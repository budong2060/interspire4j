package hu.bankmonitor.commons.interspire4j;

import static org.assertj.core.api.StrictAssertions.assertThat;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class InterspireApiTest {

	private Integer testMailListId;

	private InterspireApi interspireApi;

	private InterspireApi wrongUsertokenInterspireApi;

	@Before
	public void init() throws InterspireException {

		testMailListId = Integer.parseInt(System.getenv("INTERSPIRE_TEST_MAIL_LIST_ID"));

		interspireApi = new InterspireApi(System.getenv("INTERSPIRE_URL"), System.getenv("INTERSPIRE_USERNAME"), System.getenv("INTERSPIRE_USERTOKEN"));
		wrongUsertokenInterspireApi = new InterspireApi(System.getenv("INTERSPIRE_URL"), System.getenv("INTERSPIRE_USERNAME"), "WRONG_USERTOKEN");
	}

	@Test
	public void test() throws InterspireException {

		XmlApiTestResponse response = interspireApi.xmlApiTest();

		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(Status.SUCCESS);
		assertThat(response.getErrormessage()).isNull();
	}

	@Test
	public void testWrongUsertoken() throws InterspireException {

		XmlApiTestResponse response = wrongUsertokenInterspireApi.xmlApiTest();

		assertThat(response).isNotNull();
		assertThat(response.getStatus()).isEqualTo(Status.FAILED);
		assertThat(response.getErrormessage()).isNotEmpty();
	}

	@Test
	public void testAdd() throws InterspireException {

		AddSubscriberToListResponse response = interspireApi.addSubscriberToList(RandomStringUtils.random(15) + "@gmail.com", testMailListId, Format.HTML, true);

		assertThat(response).isNotNull();
	}

}
