package com.sacrypto.grindmoneybot.service.exchangeClient.testClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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
