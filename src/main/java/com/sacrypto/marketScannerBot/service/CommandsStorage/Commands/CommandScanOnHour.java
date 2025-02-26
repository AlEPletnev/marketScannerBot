package com.sacrypto.marketScannerBot.service.CommandsStorage.Commands;

import com.sacrypto.marketScannerBot.data.AssetStorage;
import com.sacrypto.marketScannerBot.data.ChatIdStorage;
import com.sacrypto.marketScannerBot.service.CommandsStorage.CommandStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.TelegramScanerSendler.TelegramScannerSender;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandScanOnHour implements Command {

    private final String TEXT_COMMAND = "/scanOnHour";

    @Autowired
    private CandlesHandler candlesHandler;

    @Autowired
    private AssetStorage assetStorage;

    @Autowired
    private TelegramScannerSender telegramScannerSender;

    @Autowired
    private ChatIdStorage chatIdStorage;

    @Override
    public boolean isExecuteCommand(String command) {
        return TEXT_COMMAND.equals(command);
    }

    @Override
    public String processCommand() {
        List<String> resultListRecomendation = candlesHandler.getRecommendationOnListAssets(assetStorage.getListAssetStorage(), ByBitInterval.ONE_HOUR.getInterval());
        if(resultListRecomendation.isEmpty()) {
            return "Not recommendation!!!";
        } else {
            for(String message : resultListRecomendation){
                telegramScannerSender.sendMessage(chatIdStorage.getChatId(), message);
            }
        }
        return "command is end";
    }

}
