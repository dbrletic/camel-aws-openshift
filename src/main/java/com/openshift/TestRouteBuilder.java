package com.openshift;

import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

/**
 * A Camel Java8 DSL Router
 */
public class TestRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:start")
        .routeId("SNS-Poll")
        .to("aws2-sns://test.fifo?amazonSNSClient=#amazonSNSClient");
  
      from("aws2-sqs://test-fifo?amazonSQSClient=#amazonSQSClient&delay=50&maxMessagesPerPoll=5")
        .routeId("SQS-client")
        .to("stream:out");
        
    }

}
