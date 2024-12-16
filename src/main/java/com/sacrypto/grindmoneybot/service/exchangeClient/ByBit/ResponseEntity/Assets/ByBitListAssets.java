package com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.ResponseEntity.Assets;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ByBitListAssets {

    private String ret_code;

    private String ret_msg;

    private AssetsResult result;

    private String ext_code;

    private String ext_info;

    private String time_now;

    public List<String> getListTokenSymbol(){
        return this.result.getListTokenSymbol();
    }
}
