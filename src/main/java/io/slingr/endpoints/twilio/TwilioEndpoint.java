package io.slingr.endpoints.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.slingr.endpoints.Endpoint;
import io.slingr.endpoints.framework.annotations.EndpointFunction;
import io.slingr.endpoints.framework.annotations.EndpointProperty;
import io.slingr.endpoints.framework.annotations.EndpointWebService;
import io.slingr.endpoints.framework.annotations.SlingrEndpoint;
import io.slingr.endpoints.services.rest.RestMethod;
import io.slingr.endpoints.utils.Json;
import io.slingr.endpoints.ws.exchange.WebServiceRequest;
import io.slingr.endpoints.ws.exchange.WebServiceResponse;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * <p>Twilio endpoint
 *
 * <p>Created by dgaviola on 11/05/17.
 */
@SlingrEndpoint(name = "twilio")
public class TwilioEndpoint extends Endpoint {
    private static final Logger logger = LoggerFactory.getLogger(TwilioEndpoint.class);

    @EndpointProperty
    private String accountSid;

    @EndpointProperty
    private String authToken;

    private final Random random = new Random();

    public TwilioEndpoint() {
    }

    public TwilioEndpoint(String accountSid, String authToken, String defaultAgentPhoneNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    @Override
    public void endpointStarted() {
        Twilio.init(accountSid, authToken);
    }

    @EndpointFunction(name = "_sendSms")
    public Json sendSms(Json params) {
        String to = params.string("to");
        String from = params.string("from");
        String body = params.string("body");
        Message sms = Message.creator(new PhoneNumber(to), new PhoneNumber(from), body).create();
        return Json.map().set("sid", sms.getSid());
    }

    @EndpointWebService(path = "inboundSms", methods = {RestMethod.POST})
    private WebServiceResponse inboundSms(WebServiceRequest request){
        events().send("inboundSms", request.getBody());
        return new WebServiceResponse("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Response>\n</Response>", ContentType.APPLICATION_XML.toString());
    }

}
