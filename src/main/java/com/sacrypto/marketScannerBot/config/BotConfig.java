package com.sacrypto.marketScannerBot.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class BotConfig {

    @Value("${telegrambot.name}")
    private String name;

    @Value("${telegrambot.token}")
    private String token;

}
