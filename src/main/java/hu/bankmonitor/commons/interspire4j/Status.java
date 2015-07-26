package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Status {

	@XmlEnumValue("FAILED") FAILED,

	@XmlEnumValue("SUCCESS") SUCCESS;

}
