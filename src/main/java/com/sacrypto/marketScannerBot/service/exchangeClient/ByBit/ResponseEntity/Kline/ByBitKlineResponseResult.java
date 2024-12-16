package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ByBitKlineResponseResult {

    private String category;

    private String symbol;

    @Getter(AccessLevel.NONE)
    private List<List<String>> list;

    public Bar getBar(int numberBar){
        return new Bar(new Date(Long.parseLong(this.list.get(numberBar).get(0))),     // Assign a value start time of the candle (ms)
                                Double.parseDouble(this.list.get(numberBar).get(1)),  // Assign a value open price
                                Double.parseDouble(this.list.get(numberBar).get(2)),  // Assign a value highest price
                                Double.parseDouble(this.list.get(numberBar).get(3)),  // Assign a value lowest price
                                Double.parseDouble(this.list.get(numberBar).get(4)),  // Assign a value close price. Is the last traded price when the candle is not closed
                                Double.parseDouble(this.list.get(numberBar).get(5)),  // Assign a value trade volume. Unit of contract: pieces of contract. Unit of spot: quantity of coins
                                Double.parseDouble(this.list.get(numberBar).get(6))); // Assign a value turnover. Unit of figure: quantity of quota coin
    }

    public List<Bar> getList(){
        List<Bar> listOfBar = new ArrayList<>();
        int numberOfBarsInList = this.list.size();
        for(int indexBar = 0; indexBar < numberOfBarsInList; indexBar++){
            listOfBar.add(getBar(indexBar));
        }
        return listOfBar;
    }

}
