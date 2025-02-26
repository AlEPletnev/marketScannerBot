package com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.outputRecommendation;

import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@Builder(toBuilder = true)
public class RecommendationMessage {

    @NonNull
    private final String active;

    @NonNull
    private final String entry;

    @NonNull
    private final String type;

    @NonNull
    private final Date signalDateTime;

    @NonNull
    private final ByBitInterval scanningTimeFrame;

    @NonNull
    private final String stopLoss;

    @Override
    public String toString(){
        return "Active: " + active + "\n" +
                "Entry: " + entry + "\n" +
                "Type: " + type + "\n" +
                "Signal date time: " + signalDateTime + "\n" +
                "Scanning Time Frame: " + scanningTimeFrame + "\n" +
                "Stoploss: " + stopLoss + "\n";
    }
}

