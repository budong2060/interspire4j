package hu.bankmonitor.commons.interspire4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterspireApi {

	private final Marshaller marshaller;

	private final Unmarshaller unmarshaller;

	private final String url;

	private final String username;

	private final String usertoken;

	public InterspireApi(String url, String username, String usertoken) throws InterspireException {

		this.url = url;
		this.username = username;
		this.usertoken = usertoken;

		try {
			marshaller = JAXBContext.newInstance(XmlRequest.class).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "");
			unmarshaller = JAXBContext.newInstance(AddSubscriberToListResponse.class, XmlApiTestResponse.class).createUnmarshaller();
		} catch (JAXBException e) {
			throw new InterspireException("Init error", e);
		}
	}

	public XmlApiTestResponse xmlApiTest() throws InterspireException {

		return callApi(RequestType.AUTHENTICATION, RequestMethod.XML_API_TEST, new XmlApiTestDetails(), XmlApiTestResponse.class);
	}

	public AddSubscriberToListResponse addSubscriberToList(String emailAddress, Integer mailingList, Format format, Boolean confirmed) throws InterspireException {

		return addSubscriberToList(emailAddress, mailingList, format, confirmed, Collections.emptyMap());
	}

	public AddSubscriberToListResponse addSubscriberToList(String emailAddress, Integer mailingList, Format format, Boolean confirmed, Map<Integer, String> customFields)
			throws InterspireException {

		List<CustomField> customFieldList =
				customFields.entrySet().stream().map(e -> CustomField.builder().fieldId(e.getKey()).value(e.getValue()).build()).collect(Collectors.toList());

		// @formatter:off
		AddSubscriberToListDetails details = AddSubscriberToListDetails.builder()
			.emailAddress(emailAddress)
			.mailingList(mailingList)
			.format(format)
			.confirmed(confirmed)
			.customFields(customFieldList)
			.build();
		// @formatter:on

		return callApi(RequestType.SUBSCRIBERS, RequestMethod.ADD_SUBSCRIBER_TO_LIST, details, AddSubscriberToListResponse.class);
	}

	@SuppressWarnings("unchecked")
	private <T extends Details, U extends IResponse> U callApi(RequestType requestType, RequestMethod requestMethod, T details, Class<U> responseType) throws InterspireException {

		if (log.isDebugEnabled()) {
			log.debug("callApi - requestType: {}, requestMethod: {}, details: {}", requestType, requestMethod, details);
		}

		// @formatter:off
		XmlRequest<T> request = (XmlRequest<T>) XmlRequest.builder()
				.username(username)
				.usertoken(usertoken)
				.requestType(requestType)
				.requestMethod(requestMethod)
				.details(details)
				.build();
		// @formatter:on

		byte[] xmlRaw = generateRequestXml(request);

		byte[] responseRaw = sendRequest(xmlRaw);

		return unmarshallResponse(responseRaw, responseType);
	}

	private byte[] generateRequestXml(XmlRequest<?> request) throws InterspireException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			marshaller.marshal(request, os);
		} catch (JAXBException e) {
			throw new InterspireException("Marshalling error", e);
		}

		return os.toByteArray();
	}

	private byte[] sendRequest(byte[] xmlRaw) throws InterspireException {

		try {
			Response response = Request.Post(url).body(new ByteArrayEntity(xmlRaw)).execute();
			byte[] responseRaw = EntityUtils.toByteArray(response.returnResponse().getEntity());
			log.debug("callApi - response: {}", new String(responseRaw));
			return responseRaw;
		} catch (IOException e) {
			throw new InterspireException("Communication error", e);
		}
	}

	private <T extends IResponse> T unmarshallResponse(byte[] responseRaw, Class<T> responseType) throws InterspireException {

		try {
			T response = unmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(responseRaw)), responseType).getValue();
			log.debug("callApi - response: {}", response);
			return response;
		} catch (JAXBException e) {
			throw new InterspireException("Unmarshalling error", e);
		}
	}

}
