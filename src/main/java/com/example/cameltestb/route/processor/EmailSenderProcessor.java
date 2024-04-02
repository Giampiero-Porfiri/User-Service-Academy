package com.example.cameltestb.route.processor;

import com.example.cameltestb.util.GlobalResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailSenderProcessor implements Processor {
    @Autowired
    private GlobalResources globalResources;

    @Override
    public void process(Exchange exchange) {
        log.info("Fin'ora sono state inviate {} email dall'avvio dell'app.",
                globalResources.getEmailCounter());
    }
}
