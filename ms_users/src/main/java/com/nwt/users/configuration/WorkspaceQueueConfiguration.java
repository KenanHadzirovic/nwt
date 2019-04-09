package com.nwt.users.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkspaceQueueConfiguration {

    @Bean
    public Queue workspaceQueue() {
        return new Queue("workspace.queue");
    }
}
