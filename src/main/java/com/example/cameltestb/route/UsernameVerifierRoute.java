package com.example.cameltestb.route;

import com.example.cameltestb.repository.UserRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsernameVerifierRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:verifyUsername")
                .routeId("verifyUsernameRoute")
                .bean(UserRepository.class, "existsByUsername(${header.username})");
    }
}
