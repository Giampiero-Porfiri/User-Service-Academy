package com.example.cameltestb.controller;

import com.example.cameltestb.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/batch")
public class UserBatchController {
    private final UserService userService;
    private final JobLauncher jobLauncher;
    private final Job importUserJob;

    @GetMapping("/import")
    public ResponseEntity<String> startImportUserJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(importUserJob, jobParameters);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Fallito lo start del job di import utenti.\n" + e.getMessage());
        }
        return ResponseEntity.ok().body("Job eseguito.");
    }
}
