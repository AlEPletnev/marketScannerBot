package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Assets.ByBitListAssets;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Getter
public class TokenSymbols {

    private List<String> tokenList;

    public TokenSymbols(){
        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "allAsses.json";
        File file  = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        ByBitListAssets list = null;
        try {
            list = objectMapper.readValue(file, ByBitListAssets.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.tokenList = list.getListTokenSymbol();
    }
}
