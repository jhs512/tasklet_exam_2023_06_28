package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {
    private final JobRepository jobRepository;

    @Bean
    public Job withParamJob(Step withParamStep1) {

        return new JobBuilder("withParamJob", jobRepository)
                .start(withParamStep1)
                .build();
    }

    @Bean
    public Step withParamStep1(Tasklet withParamStep1Tasklet, PlatformTransactionManager transactionManager) {
        return new StepBuilder("withParamStep1", jobRepository)
                .tasklet(withParamStep1Tasklet, transactionManager)
                .build();
    }

    @Bean
    public Tasklet withParamStep1Tasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("WithParam 테스클릿 1");

            return RepeatStatus.FINISHED;
        };
    }
}
