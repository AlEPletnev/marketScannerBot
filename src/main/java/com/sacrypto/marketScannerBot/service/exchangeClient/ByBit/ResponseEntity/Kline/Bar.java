package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
public class Bar {

    private Date startTime; // Start time of the candle (ms)

    private double openPrice; // Open price

    private double highPrice; // Highest price

    private double lowPrice; // Lowest price

    private double closePrice; // Close price. Is the last traded price when the candle is not closed

    private double volume; // Trade volume. Unit of contract: pieces of contract. Unit of spot: quantity of coins

    private double turnover; // Turnover. Unit of figure: quantity of quota coin

    private TypeOfBar type;

    private double middleOfBar;

    public Bar(Date startTime, double openPrice, double highPrice, double lowPrice, double closePrice, double volume, double turnover) {
        this.startTime = startTime;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.turnover = turnover;
        this.type = this.determineTypeBar();
        this.middleOfBar = (highPrice + lowPrice) / 2;
    }

    private TypeOfBar determineTypeBar(){
        String intervalOpenPrice = this.getRangeForPrice(this.openPrice);
        String intervalClosePrice = this.getRangeForPrice(this.closePrice);
        String resultTypeBar = intervalOpenPrice + "-" + intervalClosePrice;
        for(TypeOfBar typeOfBar : TypeOfBar.values()){
            if(resultTypeBar.equalsIgnoreCase(typeOfBar.getType())){
                return typeOfBar;
            }
        }
        return TypeOfBar.DEFAULT; // Тут еще надо будет над типом по умолчанию подумать, или просто хардом задать
    }

    private String getRangeForPrice(double price){
        double thirdOfBar = (this.highPrice - this.lowPrice) / 3;
        if(price < this.highPrice & price >= (this.highPrice - thirdOfBar)){
            return "1";
        }
        else if (price < (this.highPrice - thirdOfBar) & price >= (this.lowPrice + thirdOfBar)){
            return "2";
        }
        else { // (price < (this.lowPrice + thirdOfBar) & (price > (this.lowPrice)) //
            return "3";
        }
    }

}
