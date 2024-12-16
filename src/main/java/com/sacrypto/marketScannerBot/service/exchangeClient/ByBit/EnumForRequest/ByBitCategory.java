package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest;

public enum ByBitCategory {

    SPOT("spot"),
    LINEAR("linear"),
    INVERSE("inverse");

    private String category;

    private ByBitCategory(String category){
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }

}
