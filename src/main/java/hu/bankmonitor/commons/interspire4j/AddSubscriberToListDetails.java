package hu.bankmonitor.commons.interspire4j;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
@XmlRootElement
public class AddSubscriberToListDetails extends Details {

	/**
	 * The email address of the contact being added. (Required)
	 */
	@XmlElement(name = "emailaddress", required = true)
	private String emailAddress;

	/**
	 * The list that the contact is located within. (Required)
	 */
	@XmlElement(name = "mailinglist", required = true)
	private int mailingList;

	/**
	 * The format of the email campaigns that this contact prefers to receive (html or h or text or t) (defaults to text)
	 */
	private Format format;

	/**
	 * Sets the confirmation status of the subscriber to confirmed or not (yes or y or true or 1) (Not required, default to unconfirmed)
	 */
	private Boolean confirmed;

	@XmlElementWrapper(name = "customfields")
	@XmlElement(name = "item")
	private List<CustomField> customFields;

}
