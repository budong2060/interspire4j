package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomField {

	/**
	 * The id of the custom field being added.
	 */
	@XmlElement(name = "fieldid")
	private int fieldId;

	/**
	 * The value to be added to this custom field.
	 */
	private String value;

}
