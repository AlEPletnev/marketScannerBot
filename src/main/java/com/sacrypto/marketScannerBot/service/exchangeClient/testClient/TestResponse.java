package com.sacrypto.marketScannerBot.service.exchangeClient.testClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
//@JsonIgnoreProperties({"headers", "url"})
public class TestResponse {
    @JsonProperty("args")
    private TestArgs args;

//    @JsonSetter("headers")
//    private TestHeaders headers;
//
//    @JsonSetter("url")
//    private String url;

//    @Override
//    public String toString() {
//        return "TestResponse{" +
//                "args=" + args +
//                ", headers=" + headers +
//                ", url='" + url + '\'' +
//                '}';
//    }

}
