package com.swedbank.academy.springlesson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.swedbank.academy.springlesson.person", "com.swedbank.academy.springlesson.group"})
public class JPAConfig {
}
