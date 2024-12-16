package com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AssetsResult {

    private List<TokenResult> quoteTokenResult;

    private String version;

    public List<String> getListTokenSymbol(){
        for(TokenResult tokenResult : this.getQuoteTokenResult()){
            if(tokenResult.getTokenId().equalsIgnoreCase("USDT")){
                return tokenResult.getListTokenSymbol();
            }
        }
        return new ArrayList<>();
    }
}
