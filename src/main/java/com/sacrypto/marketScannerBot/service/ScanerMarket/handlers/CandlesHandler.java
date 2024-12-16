package com.sacrypto.marketScannerBot.service.ScanerMarket.handlers;

import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitCategory;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.RequestClients.FeignByBitClientForKline;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline.Bar;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline.ByBitKline;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline.TypeOfBar;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Getter
@Setter
public class CandlesHandler {

    @Autowired
    private FeignByBitClientForKline client;

    // Example assetName - BTCUSDT
    public String getRecommendation(String assetName){
        ByBitKline kline = client.getKline(ByBitCategory.SPOT.getCategory(), assetName, ByBitInterval.THREE_MINUTE.getInterval(),"3");
        String retMessage = kline.getRetMsg();
        switch(retMessage){
            case "OK":
                // Вообще стоит еще над форматов вывода сообщения подумать как следует
                if(this.isEntryToBuy(kline)){
                    // Тут стоит использовать форматируемую строку
                    String infoMessage = "Long active: " + kline.getResult().getSymbol() + " "
                                                         + kline.getResult().getCategory() + " "
                                                         + kline.getResult().getBar(1).getStartTime() + " "
                                                         + "put a stop: " + kline.getResult().getBar(1).getLowPrice();
                    return infoMessage;
                }
                if(this.isEntryToSale(kline)){
                    String infoMessage = "Short active: " + kline.getResult().getSymbol() + " "
                                                          + kline.getResult().getCategory() + " "
                                                          + kline.getResult().getBar(1).getStartTime()+ " "
                                                          + "put a stop: " + kline.getResult().getBar(1).getHighPrice();
                    return infoMessage;
                }
                break;
            default:

                break;
        }
        return "nothing";
    }

    private boolean isEntryToBuy(ByBitKline kline){
        List<Bar> listOfBar = kline.getListOfBars();
        if(listOfBar.get(0).getType() == TypeOfBar.THREE_ONE & listOfBar.get(1).getType() == TypeOfBar.THREE_ONE &
           listOfBar.get(1).getVolume() > listOfBar.get(0).getVolume() & listOfBar.get(1).getMiddleOfBar() > listOfBar.get(0).getHighPrice()){
            return true;
        }
        return false;
    }

    private boolean isEntryToSale(ByBitKline kline){
        List<Bar> listOfBar = kline.getListOfBars();
        if(listOfBar.get(0).getType() == TypeOfBar.ONE_THREE & listOfBar.get(1).getType() == TypeOfBar.ONE_THREE &
           listOfBar.get(1).getVolume() > listOfBar.get(0).getVolume() & listOfBar.get(1).getMiddleOfBar() < listOfBar.get(0).getLowPrice()){
            return true;
        }
        return false;
    }



}
