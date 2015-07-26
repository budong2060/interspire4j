package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class XmlApiTestDetails extends Details {

	// üres elemmel nem működik a hívás
	private String test = "1";

}
