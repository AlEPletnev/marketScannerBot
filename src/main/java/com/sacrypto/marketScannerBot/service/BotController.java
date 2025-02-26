package com.sacrypto.marketScannerBot.service;

import com.sacrypto.marketScannerBot.config.BotConfig;
import com.sacrypto.marketScannerBot.data.AssetStorage;
import com.sacrypto.marketScannerBot.data.ChatIdStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.Commands.Command;
import com.sacrypto.marketScannerBot.service.ScanerMarket.ScannerMarket;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
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
    private CommandStorage commandStorage;

    @Autowired
    private ChatIdStorage chatId;

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
            this.chatId.setChatId(update.getMessage().getChatId());
            String textCommand = update.getMessage().getText();
            String responseMessage = "Command not received";
            for(Command currentCommand : this.commandStorage.getCommandsList()){
                if(currentCommand.isExecuteCommand(textCommand)){
                    responseMessage = currentCommand.processCommand();
                }
            }
            sendMessage(this.chatId.getChatId(),responseMessage);
        }
    }

}
