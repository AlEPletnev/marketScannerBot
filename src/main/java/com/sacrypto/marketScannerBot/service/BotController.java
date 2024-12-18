package com.sacrypto.marketScannerBot.service;

import com.sacrypto.marketScannerBot.config.BotConfig;
import com.sacrypto.marketScannerBot.service.ScanerMarket.ScannerMarket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotController extends TelegramLongPollingBot {

    @Autowired
    private BotConfig config;

    @Autowired
    private ScannerMarket scanner;

    private volatile long chatId;

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @PostConstruct
    public void init() throws TelegramApiException{
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try{
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMessage(long chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try{
            this.execute(message);
        } catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            switch(messageText){
                case "/start":
                    scanner.enableScanning(chatId);
                    break;
                case "/echo":
                    sendMessage(chatId,messageText);
                    break;
                case "/stop":
                    scanner.disableScanning();
                    break;
                case "/test":
                    String message = "test echo";
                    sendMessage(chatId,message);
                    break;
                default:
                    sendMessage(chatId, "Command not received");
                    break;
            }
        }
    }

}
