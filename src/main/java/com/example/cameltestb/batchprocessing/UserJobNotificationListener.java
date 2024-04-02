package com.example.cameltestb.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserJobNotificationListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Sto per iniziare il job di trasferimento utenti.");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Ho finito il job di trasferimento utenti.");
    }
}
