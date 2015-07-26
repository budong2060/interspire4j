package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class XmlApiTestResponse implements IResponse {

	@lombok.Data
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Data {

		private int userid;

		private String username;

	}

	private Status status;

	private String errormessage;

	private Data data;

}
