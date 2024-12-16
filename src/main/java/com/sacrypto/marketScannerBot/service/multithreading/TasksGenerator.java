package com.sacrypto.marketScannerBot.service.multithreading;

import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksGenerator {

    @Autowired
    private CandlesHandler candlesHandler;

    public AssetTask createTask(String assetName){
        return new AssetTask(this.candlesHandler,assetName);
    }
}
