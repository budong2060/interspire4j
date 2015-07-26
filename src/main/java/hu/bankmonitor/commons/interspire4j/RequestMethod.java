package hu.bankmonitor.commons.interspire4j;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum RequestMethod {

	/**
	 * Add Subscriber to a List
	 */
	@XmlEnumValue("AddSubscriberToList") ADD_SUBSCRIBER_TO_LIST,

	/**
	 * Delete Subscriber
	 */
	@XmlEnumValue("DeleteSubscriber") DELETE_SUBSCRIBER,

	/**
	 * Get Custom Field Data
	 */
	@XmlEnumValue("GetCustomFields") GET_CUSTOM_FIELDS,

	/**
	 * Get Lists
	 */
	@XmlEnumValue("GetLists") GET_LISTS,

	/**
	 * Get Subscribers
	 */
	@XmlEnumValue("GetSubscribers") GET_SUBSCRIBERS,

	/**
	 * Is Contact on List
	 */
	@XmlEnumValue("IsSubscriberOnList") IS_SUBSCRIBER_ON_LIST,

	/**
	 * Check Token Works
	 */
	@XmlEnumValue("xmlapitest") XML_API_TEST;

}
