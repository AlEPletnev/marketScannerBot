package com.sacrypto.marketScannerBot.service.ScanerMarket.handlers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline.ByBitKline;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CandlesHandlerTest {

    @Autowired
    public CandlesHandler candlesHandler;

    @Test
    public void sellSignalTest(){
        ByBitKline kline = this.getKline("testKlineShort.json");
        boolean result = candlesHandler.isEntryToSale(kline);
        Assertions.assertTrue(result);
    }

    @Test
    public void buySignalTest(){
        ByBitKline kline = this.getKline("testKlineLong.json");
        boolean result = candlesHandler.isEntryToBuy(kline);
        Assertions.assertTrue(result);
    }

    public ByBitKline getKline(String fileName){
        String path = "src" + File.separator + "test" + File.separator + "resources" + File.separator + fileName;
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        ByBitKline kline = null;
        try{
            kline = objectMapper.readValue(file, ByBitKline.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kline;
    }
}
