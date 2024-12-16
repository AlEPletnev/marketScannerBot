package com.sacrypto.grindmoneybot.service.exchangeClient.testClient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TestHeaders {

    @JsonProperty("host")
    private String host;

    @JsonProperty("x-request-start")
    private String xRequestStart;

    @JsonProperty("connection")
    private String connection;

    @JsonProperty("x-forwarded-proto")
    private String proto;

    @JsonProperty("x-forwarded-port")
    private String xForwardedPort;

    @JsonProperty("x-amzn-trace-id")
    private String id;

    @JsonProperty("content-type")
    private String type;

    @JsonProperty("accept")
    private String accept;

    @JsonProperty("user-agent")
    private String userAgent;

    @Override
    public String toString() {
        return "TestHeaders{" +
                "host='" + host + '\'' +
                ", xRequestStart='" + xRequestStart + '\'' +
                ", connection='" + connection + '\'' +
                ", proto='" + proto + '\'' +
                ", xForwardedPort='" + xForwardedPort + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", accept='" + accept + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
