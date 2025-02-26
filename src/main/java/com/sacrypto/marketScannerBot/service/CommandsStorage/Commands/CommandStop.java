package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.ScannerMarket;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandStop implements Command {

    private final String TEXT_COMMAND = "/stop";

    @Autowired
    private ScannerMarket scannerMarket;

    @Override
    public boolean isExecuteCommand(String command) {
        return TEXT_COMMAND.equals(command);
    }

    @Override
    public String processCommand() {
        this.scannerMarket.disableScanning();
        return "Market scanner is stopping!";
    }
}