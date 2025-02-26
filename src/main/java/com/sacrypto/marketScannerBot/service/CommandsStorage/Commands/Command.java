package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

public interface Command {

    boolean isExecuteCommand(String command);

    String processCommand();

}
