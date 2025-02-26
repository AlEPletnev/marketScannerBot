package com.sacrypto.marketScannerBot.service.ScanerMarket.handlers;

import com.sacrypto.marketScannerBot.data.TimeFrameScanningStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.outputRecommendation.RecommendationMessage;
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

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@Setter
public class CandlesHandler {

    @Autowired
    private FeignByBitClientForKline client;

    @Autowired
    private TimeFrameScanningStorage timeFrameScanningStorage;

    // Example assetName - BTCUSDT
    public String getRecommendation(String assetName){
        ByBitKline kline = client.getKline(ByBitCategory.SPOT.getCategory(), assetName, ByBitInterval.THREE_MINUTE.getInterval(),"3");
        String retMessage = kline.getRetMsg();
//        RecommendationMessage message = RecommendationMessage.builder()
//                .a(123)
//                .b("test")
//                .build();
        switch(retMessage){
            case "OK":
                // Вообще стоит еще над форматом вывода сообщения подумать как следует
                if(this.isEntryToBuy(kline)){
                    // Тут стоит использовать форматируемую строку
//                    String infoMessage = "Long active: " + kline.getResult().getSymbol() + " "
//                                                         + kline.getResult().getCategory() + " "
//                                                         + kline.getResult().getBar(1).getStartTime() + " "
//                                                         + "put a stop: " + kline.getResult().getBar(1).getLowPrice();
//                    return infoMessage;
                    RecommendationMessage recommendationMessage = RecommendationMessage.builder()
                            .active(kline.getResult().getSymbol())
                            .entry("LONG")
                            .type(kline.getResult().getCategory())
                            .signalDateTime(kline.getResult().getBar(0).getStartTime())
                            .scanningTimeFrame(timeFrameScanningStorage.getTimeFrameForScanning())
                            .stopLoss(String.valueOf(kline.getResult().getBar(2).getLowPrice()))
                            .build();

                    return recommendationMessage.toString();
                }
                if(this.isEntryToSale(kline)){
//                    String infoMessage = "Short active: " + kline.getResult().getSymbol() + " "
//                                                          + kline.getResult().getCategory() + " "
//                                                          + kline.getResult().getBar(1).getStartTime()+ " "
//                                                          + "put a stop: " + kline.getResult().getBar(1).getHighPrice();
//                    return infoMessage;

                    RecommendationMessage recommendationMessage = RecommendationMessage.builder()
                            .active(kline.getResult().getSymbol())
                            .entry("SHORT")
                            .type(kline.getResult().getCategory())
                            .signalDateTime(kline.getResult().getBar(0).getStartTime())
                            .scanningTimeFrame(timeFrameScanningStorage.getTimeFrameForScanning())
                            .stopLoss(String.valueOf(kline.getResult().getBar(2).getHighPrice()))
                            .build();

                    return recommendationMessage.toString();
                }
                break;
            default:

                break;
        }
        return "nothing";
    }

    /*
     * Only for preliminary test
     */
    public String getRecommendation(String assetName, String analyticsTimeFrame){
        ByBitKline kline = client.getKline(ByBitCategory.SPOT.getCategory(), assetName, analyticsTimeFrame,"3");
        String retMessage = kline.getRetMsg();
        switch(retMessage){
            case "OK":
                // Вообще стоит еще над форматом вывода сообщения подумать как следует
                if(this.isEntryToBuy(kline)){
                    // Тут стоит использовать форматируемую строку
//                    String infoMessage = "Long active: " + kline.getResult().getSymbol() + " "
//                            + kline.getResult().getCategory() + " "
//                            + kline.getResult().getBar(1).getStartTime() + " "
//                            + "put a stop: " + kline.getResult().getBar(1).getLowPrice();
//                    return infoMessage;
                    RecommendationMessage recommendationMessage = RecommendationMessage.builder()
                            .active(kline.getResult().getSymbol())
                            .entry("LONG")
                            .type(kline.getResult().getCategory())
                            .signalDateTime(kline.getResult().getBar(0).getStartTime())
                            .scanningTimeFrame(timeFrameScanningStorage.getTimeFrameForScanning())
                            .stopLoss(String.valueOf(kline.getResult().getBar(2).getLowPrice()))
                            .build();

                    return recommendationMessage.toString();
                }
                if(this.isEntryToSale(kline)){
//                    String infoMessage = "Short active: " + kline.getResult().getSymbol() + " "
//                            + kline.getResult().getCategory() + " "
//                            + kline.getResult().getBar(1).getStartTime()+ " "
//                            + "put a stop: " + kline.getResult().getBar(1).getHighPrice();
//                    return infoMessage;
                    RecommendationMessage recommendationMessage = RecommendationMessage.builder()
                            .active(kline.getResult().getSymbol())
                            .entry("SHORT")
                            .type(kline.getResult().getCategory())
                            .signalDateTime(kline.getResult().getBar(0).getStartTime())
                            .scanningTimeFrame(timeFrameScanningStorage.getTimeFrameForScanning())
                            .stopLoss(String.valueOf(kline.getResult().getBar(2).getHighPrice()))
                            .build();

                    return recommendationMessage.toString();
                }
                break;
            default:

                break;
        }
        return "nothing";
    }

    public List<String> getRecommendationOnListAssets(List<String> listAssets, String analyticsTimeFrame){
        List<String> resultListRecommendation = new ArrayList<>(listAssets.size());
        for(String nameAsset : listAssets){
            System.out.println("Смотрим актив: " + nameAsset);
            String resultRecommendationOneAsset = this.getRecommendation(nameAsset,analyticsTimeFrame);
            if(!resultRecommendationOneAsset.equals("nothing")){
                resultListRecommendation.add(resultRecommendationOneAsset);
            }
        }
        return resultListRecommendation;
    }

    public boolean isEntryToBuy(ByBitKline kline){
        List<Bar> listOfBar = kline.getListOfBars();
        if(listOfBar.get(2).getType() == TypeOfBar.THREE_ONE & listOfBar.get(1).getType() == TypeOfBar.THREE_ONE &
           listOfBar.get(1).getVolume() > listOfBar.get(2).getVolume() & listOfBar.get(1).getMiddleOfBar() > listOfBar.get(2).getHighPrice()){
            return true;
        }
        return false;
    }

    public boolean isEntryToSale(ByBitKline kline){
        List<Bar> listOfBar = kline.getListOfBars();
        if(listOfBar.get(2).getType() == TypeOfBar.ONE_THREE & listOfBar.get(1).getType() == TypeOfBar.ONE_THREE &
                listOfBar.get(1).getVolume() > listOfBar.get(2).getVolume() & listOfBar.get(1).getMiddleOfBar() < listOfBar.get(2).getLowPrice()){
            return true;
        }
        return false;
    }



}
