package com.sacrypto.marketScannerBot.data;

import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import org.springframework.stereotype.Component;

@Component
public class TimeFrameScanningStorage {

    private volatile ByBitInterval timeFrameForScanning = ByBitInterval.THREE_MINUTE;

    public void setTimeFrameForScanning(ByBitInterval timeFrameForScanning){
        this.timeFrameForScanning = timeFrameForScanning;
    }

    public ByBitInterval getTimeFrameForScanning(){
        return timeFrameForScanning;
    }
}
