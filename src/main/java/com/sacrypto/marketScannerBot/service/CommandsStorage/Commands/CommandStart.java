package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

import com.sacrypto.marketScannerBot.data.ChatIdStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.ScannerMarket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandStart implements Command{

    private final String TEXT_COMMAND = "/start";

    @Autowired
    private ChatIdStorage chatId;

    @Autowired
    private ScannerMarket scannerMarket;

    @Override
    public boolean isExecuteCommand(String command) {
        String textCommand = "/start";
        return textCommand.equals(command);
    }

    @Override
    public String processCommand() {
        scannerMarket.enableScanning(this.chatId.getChatId());
        return "Market scanner is running!";
    }

}
