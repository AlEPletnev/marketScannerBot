package com.sacrypto.marketScannerBot.service.ScanerMarket.TelegramScanerSendler;

import com.sacrypto.marketScannerBot.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramScannerSender extends DefaultAbsSender {

    @Autowired
    private BotConfig config;

    public TelegramScannerSender() {
        this(new DefaultBotOptions());
    }

    public TelegramScannerSender(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    public void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try{
            this.execute(message);
        } catch(TelegramApiException e){
            e.printStackTrace();
        }
    }
}
