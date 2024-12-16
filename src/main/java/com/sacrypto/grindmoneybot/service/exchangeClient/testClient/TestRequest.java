package com.sacrypto.grindmoneybot.service.exchangeClient.testClient;

import org.apache.http.util.Args;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="postman-echo", url = "https://postman-echo.com")
public interface TestRequest {

    @GetMapping(path = "/get", consumes = "application/json")
    TestResponse getEcho(@RequestParam("foo") String foo,
                         @RequestParam("bar") String bar);
}
