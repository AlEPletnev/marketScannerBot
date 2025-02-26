package com.sacrypto.marketScannerBot.service.CommandsStorage;

import com.sacrypto.marketScannerBot.service.CommandsStorage.Commands.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandStorage {

    private final List<Command> commandsList;

    public void addCommand(Command command){
        commandsList.add(command);
    }

    public List<Command> getCommandsList(){
        return new ArrayList<>(commandsList);
    }

}
