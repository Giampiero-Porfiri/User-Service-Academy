package com.example.cameltestb.route;

import com.example.cameltestb.route.processor.RegistrationCounterProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RegistrationCounterRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:gianniRegistrationCounterTimer")
                .routeId("gianniRegistrationCounterTimer")
                .delayer(60000L)
                .bean(RegistrationCounterProcessor.class, "process")
                .to("log:gianniRegistrationCounterLog");
    }
}
