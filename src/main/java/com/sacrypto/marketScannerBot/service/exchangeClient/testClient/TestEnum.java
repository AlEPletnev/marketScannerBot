package com.sacrypto.marketScannerBot.service.exchangeClient.testClient;

public enum TestEnum {

    TEST_1("test1"),
    TEST_2("test2"),
    TEST_3("test3");

    private String test;

    TestEnum(String test){
        this.test = test;
    }

    String getTest(){
        return this.test;
    }
}

//public class Test{
//    public static void Test(String[] args) {
//        String testPrint = TestEnum.TEST_1.getTest();
//    }
//}