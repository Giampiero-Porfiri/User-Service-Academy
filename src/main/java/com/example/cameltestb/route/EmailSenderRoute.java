package com.example.cameltestb.route;

import com.example.cameltestb.route.processor.EmailSenderProcessor;
import com.example.cameltestb.util.GlobalResources;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderRoute extends RouteBuilder {
    @Autowired
    private GlobalResources globalResources;

    @Override
    public void configure() {
        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "Errore di timeout durante l'invio dell'email: ${exception.message}");

        from("direct:sendEmail")
                .routeId("sendEmailRoute")
                .toD("smtps://{{spring.mail.host}}:{{spring.mail.port}}?username={{spring.mail.username}}" +
                        "&password={{spring.mail.password}}&mail.smtp.auth={{spring.mail.properties.mail.smtp.auth}}" +
                        "&mail.smtp.starttls.enable={{spring.mail.properties.mail.smtp.starttls.enable}}")
                .process(exchange -> globalResources.incrementEmailCounter())
                .log("Email inviata con successo.")
                .bean(EmailSenderProcessor.class, "process");
    }
}
