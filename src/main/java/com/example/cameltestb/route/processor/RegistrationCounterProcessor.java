package com.example.cameltestb.route.processor;

import com.example.cameltestb.util.GlobalResources;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegistrationCounterProcessor implements Processor {
    @Autowired
    private GlobalResources globalResources;

    @Override
    public void process(Exchange exchange) {
        log.info("Fin'ora si sono registrati {} utenti di nome Gianni dall'avvio dell'app.",
                globalResources.getGianniCounter());
    }
}
