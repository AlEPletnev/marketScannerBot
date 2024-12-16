package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ByBitKline {

    private String retCode;

    private String retMsg;

    private ByBitKlineResponseResult result;

    private String time;

    public Bar getBar(int numberBar){
        return result.getBar(numberBar);
    }

    public List<Bar> getListOfBars(){
        return result.getList();
    }
}
