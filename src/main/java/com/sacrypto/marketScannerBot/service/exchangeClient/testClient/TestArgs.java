package com.sacrypto.marketScannerBot.service.exchangeClient.testClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TestArgs implements Serializable {

    @JsonProperty("foo")
    private String args;

    @JsonProperty("bar")
    private String foo;

    @Override
    public String toString() {
        return "TestArgs{" +
                "args='" + args + '\'' +
                ", foo='" + foo + '\'' +
                '}';
    }
}
