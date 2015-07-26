package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@lombok.Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class AddSubscriberToListResponse implements IResponse {

	private Status status;

	private String errormessage;

	@XmlElement(name = "data")
	private Integer subscriberId;

}
