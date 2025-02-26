package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandTestScan implements Command {

    private final String TEXT_COMMAND = "/testScan";

    @Autowired
    private CandlesHandler candlesHandler;

    @Override
    public boolean isExecuteCommand(String command){
        return TEXT_COMMAND.equals(command);
    }

    @Override
    public String processCommand(){
        return candlesHandler.getRecommendation("BTCUSDT", ByBitInterval.ONE_HOUR.getInterval());
    }

}
