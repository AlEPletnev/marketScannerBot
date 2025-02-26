package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands.SetterTimeFrame;

import com.sacrypto.marketScannerBot.data.TimeFrameScanningStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.Commands.Command;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandSetScanOnThreeMinute implements Command {

    private final String TEXT_COMMAND = "/setScanOnThreeMinute";

    @Autowired
    private TimeFrameScanningStorage timeFrameScanningStorage;

    @Override
    public boolean isExecuteCommand(String command) {
        return TEXT_COMMAND.equals(command);
    }

    @Override
    public String processCommand() {
        timeFrameScanningStorage.setTimeFrameForScanning(ByBitInterval.THREE_MINUTE);
        return "Timeframe for scanning: " + timeFrameScanningStorage.getTimeFrameForScanning();
    }
}
