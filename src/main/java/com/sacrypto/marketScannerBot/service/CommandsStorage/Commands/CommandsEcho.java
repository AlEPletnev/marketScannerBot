package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

import org.springframework.stereotype.Component;

@Component
public class CommandsEcho implements Command {

    private final String TEXT_COMMAND = "/echo";

    @Override
    public boolean isExecuteCommand(String command) {
        return TEXT_COMMAND.equals(command);
    }

    @Override
    public String processCommand() {
        return "echo";
    }

}
