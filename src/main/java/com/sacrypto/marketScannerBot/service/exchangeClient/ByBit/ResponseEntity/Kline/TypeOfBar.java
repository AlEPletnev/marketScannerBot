package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline;

public enum TypeOfBar {

    ONE_ONE("1-1"),
    ONE_TWO("1-2"),
    ONE_THREE("1-3"),
    TWO_ONE("2-1"),
    TWO_TWO("2-2"),
    TWO_THREE("2-3"),
    THREE_ONE("3-1"),
    THREE_TWO("3-2"),
    THREE_THREE("3-3"),
    DEFAULT("X-X");

    private String type;

    private TypeOfBar(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
