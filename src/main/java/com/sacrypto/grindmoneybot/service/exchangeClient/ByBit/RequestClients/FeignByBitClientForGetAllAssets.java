package com.sacrypto.grindmoneybot.service.exchangeClient.ByBit.RequestClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="FeignByBitClientForGetAllAssets", url = "https://api2.bybit.com/spot/api/basic/symbol_list_v3")
public interface FeignByBitClientForGetAllAssets {

    /*
    * GET https://api2.bybit.com/spot/api/basic/symbol_list_v3
    */


    @GetMapping("")
    public String getAllAssets();

}
