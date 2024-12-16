package com.sacrypto.marketScannerBot.service.multithreading;

import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;

import java.util.concurrent.Callable;

public class AssetTask implements Callable<String> {

    private final CandlesHandler candlesHandler;

    private final String nameAssets;

    public AssetTask(CandlesHandler candlesHandler, String nameAssets){
        this.candlesHandler = candlesHandler;
        this.nameAssets = nameAssets;
    }

    @Override
    public String call() throws Exception{
        return this.candlesHandler.getRecommendation(this.nameAssets);
    }
}
