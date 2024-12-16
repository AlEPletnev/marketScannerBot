package com.sacrypto.grindmoneybot.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "telegrambot")
@PropertySource("application.properties")
@Data
public class BotConfig {

    private String name;

    private String token;

}
