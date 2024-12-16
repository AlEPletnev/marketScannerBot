package com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenResult {
    private String tokenId;
    private String tokenName;
    private String tokenFullName;
    private List<TokenSymbols> quoteTokenSymbols;
    private List<String> quoteTags;
    private String tokenType;
    private String zeroFeeType;

    public List<String> getListTokenSymbol(){
        List<String> tokenList = new ArrayList<>();
        for(TokenSymbols token : this.getQuoteTokenSymbols()){
            tokenList.add(token.getSi());
        }
        return tokenList;
    }
}
