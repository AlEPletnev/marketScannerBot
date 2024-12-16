package com.sacrypto.grindmoneybot.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets.ByBitListAssets;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Getter
@ToString
public class AssetStorage {

    private List<String> listAssetStorage;

    public AssetStorage(){
        this.listAssetStorage = this.fillListAssets();
    }

    private List<String> fillListAssets() {
        String path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "allAsses.json";
        File file  = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        ByBitListAssets list  = null;
        try {
            list = objectMapper.readValue(file,ByBitListAssets.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.getListTokenSymbol();
    }
}
