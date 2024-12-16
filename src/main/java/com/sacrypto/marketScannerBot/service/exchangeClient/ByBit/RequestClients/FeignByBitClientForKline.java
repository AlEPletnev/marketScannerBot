package com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.RequestClients;

import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.ResponseEntity.Kline.ByBitKline;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="FeignByBitClientForKline", url = "https://api.bybit.com")
public interface FeignByBitClientForKline {

    /*
    *** test address https://api-testnet.bybit.com/v5/market/kline?category=spot&symbol=BTCUSDT&interval=3&limit=4
    *** use address https://api.bybit.com/v5/market/kline?category=spot&symbol=BTCUSDT&interval=3&limit=4
     */

//    @GetMapping("/v5/market/kline")
//    public String getKline(@RequestParam(name = "category") String category,
//                           @RequestParam(name = "symbol") String symbol,
//                           @RequestParam(name = "interval") String interval,
//                           @RequestParam(name = "limit") String limit);

    @GetMapping("/v5/market/kline")
    public ByBitKline getKline(@RequestParam(name = "category") String category,
                               @RequestParam(name = "symbol") String symbol,
                               @RequestParam(name = "interval") String interval,
                               @RequestParam(name = "limit") String limit);
}
