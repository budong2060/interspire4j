package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "xmlrequest")
public class XmlRequest<T extends Details> {

	/**
	 * The user name used to login to the Interspire Email Marketer. (Required)
	 */
	@XmlElement(required = true)
	private String username;

	/**
	 * The unique token assigned to the user account used above. (Required)
	 */
	@XmlElement(required = true)
	private String usertoken;

	/**
	 * The name of the API file in question. (Required)
	 */
	@XmlElement(name = "requesttype", required = true)
	private RequestType requestType;

	/**
	 * The name of the function being called. (Required)
	 */
	@XmlElement(name = "requestmethod", required = true)
	private RequestMethod requestMethod;

	@XmlElement(required = true)
	private T details;

}
