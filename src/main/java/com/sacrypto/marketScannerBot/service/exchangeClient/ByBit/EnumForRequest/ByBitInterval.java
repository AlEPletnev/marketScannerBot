package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest;

public enum ByBitInterval {

    ONE_MINUTE("1"),
    THREE_MINUTE("3"),
    FIVE_MINUTE("5"),
    FIFTEEN_MINUTE("15"),
    THIRTY_MINUTE("30"),
    ONE_HOUR("60"),
    TWO_HOUR("120"),
    FOUR_HOUR("240"),
    SIX_HOUR("360"),
    TWELVE_HOUR("720"),
    DAY("D"),
    MONTH("M"),
    WEEK("W");

    private String interval;

    private ByBitInterval(String interval){
        this.interval = interval;
    }

    public String getInterval(){
        return this.interval;
    }

}
