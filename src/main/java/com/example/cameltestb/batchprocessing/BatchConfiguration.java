package com.example.cameltestb.batchprocessing;

import com.example.cameltestb.batchprocessing.record.UserRecord;
import com.example.cameltestb.dto.UserDto;
import com.example.cameltestb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {
    @Autowired
    private UserService userService;

    @Bean
    public FlatFileItemReader<UserRecord> reader() {
        return new FlatFileItemReaderBuilder<UserRecord>()
                .name("personItemReader")
                .resource(new ClassPathResource("starting-users.csv"))
                .delimited()
                .names("name", "surname", "username", "password", "email")
                .targetType(UserRecord.class)
                .build();
    }

    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public ItemWriter<UserDto> writer() {
        return users -> users.forEach(userService::register);
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<UserRecord> reader, UserItemProcessor processor,
                      ItemWriter<UserDto> writer) {
        return new StepBuilder("step1", jobRepository)
                .<UserRecord, UserDto> chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1, UserJobNotificationListener listener) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }
}
