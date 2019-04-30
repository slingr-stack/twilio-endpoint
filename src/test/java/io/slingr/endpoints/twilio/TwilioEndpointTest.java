package io.slingr.endpoints.twilio;

import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.utils.tests.EndpointTests;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

@Ignore("For dev purposes only")
public class TwilioEndpointTest {

    private static EndpointTests test;
    private static TwilioEndpoint endpoint;

    @BeforeClass
    public static void init() throws Exception {
        test = EndpointTests.start(new io.slingr.endpoints.twilio.Runner(), "test.properties");
        endpoint = (TwilioEndpoint) test.getEndpoint();
    }

    @Test
    public void testSendSms() {
        Json params = Json.map()
                .set("from", "+19543888933")
                .set("to", "+5492616278188")
                .set("body", "test message!");
        Json res = endpoint.sendSms(params);
        assertNotNull(res);
        assertNotNull(res.string("sid"));
    }
}
