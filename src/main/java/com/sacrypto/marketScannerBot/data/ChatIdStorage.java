package com.sacrypto.marketScannerBot.data;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

// !!!!! Пока для предварительного варианта оставлю, но потом, как с нормальной архитектурой разберусь, поменяю
@Component
@Setter
@Getter
public class ChatIdStorage {

    private volatile long chatId;

}
