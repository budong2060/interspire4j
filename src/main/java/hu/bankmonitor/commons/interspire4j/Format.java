package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Format {

	@XmlEnumValue("text") TEXT,

	@XmlEnumValue("html") HTML;

}
