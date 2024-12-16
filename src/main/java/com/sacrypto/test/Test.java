package com.sacrypto.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets.ByBitListAssets;
import com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets.TokenSymbols;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        //File file = new File("C:\\Users\\AlexPC\\Desktop\\javaProject\\grindmoneybot\\src\\main\\resources\\allAsses.json");
        //File file  = new File("src\\main\\resources\\allAsses.json");
        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "allAsses.json";
        File file  = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        ByBitListAssets list = objectMapper.readValue(file,ByBitListAssets.class);
        System.out.println(list.getListTokenSymbol());
//        int index = 1;
//        for(TokenSymbols symbolToken : list.getResult().getQuoteTokenResult().get(0).getQuoteTokenSymbols()){
//            System.out.println(index + ": " + symbolToken.getSi());
//            index += 1;
//        }

        //System.out.println(list);
    }
}
