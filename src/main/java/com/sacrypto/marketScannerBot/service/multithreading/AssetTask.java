package com.sacrypto.marketScannerBot.service.multithreading;

import com.sacrypto.marketScannerBot.data.TimeFrameScanningStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;

import java.util.concurrent.Callable;

public class AssetTask implements Callable<String> {

    private final CandlesHandler candlesHandler;

    private final String nameAssets;

    private final ByBitInterval interval;

    public AssetTask(CandlesHandler candlesHandler, String nameAssets, ByBitInterval interval){
        this.candlesHandler = candlesHandler;
        this.nameAssets = nameAssets;
        this.interval = interval;
    }

    @Override
    public String call() throws Exception{
        System.out.println(this.nameAssets + " " + this.interval.getInterval());
        return this.candlesHandler.getRecommendation(this.nameAssets, this.interval.getInterval());
    }
}
