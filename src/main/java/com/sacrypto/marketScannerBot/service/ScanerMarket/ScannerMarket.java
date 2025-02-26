package com.sacrypto.marketScannerBot.service.ScanerMarket;

import com.sacrypto.marketScannerBot.data.AssetStorage;
import com.sacrypto.marketScannerBot.service.ScanerMarket.TelegramScanerSendler.TelegramScannerSender;
import com.sacrypto.marketScannerBot.service.ScanerMarket.handlers.CandlesHandler;
import com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval;
import com.sacrypto.marketScannerBot.service.multithreading.TasksGenerator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.sacrypto.marketScannerBot.service.exchangeClient.ByBit.EnumForRequest.ByBitInterval.THREE_MINUTE;


@Component
public class ScannerMarket extends Thread{

    @Autowired
    private AssetStorage assetStorage;

    @Autowired
    private TelegramScannerSender telegramScannerSender;

    @Autowired
    private TasksGenerator tasksGenerator;

    private volatile long chatId;

    private volatile boolean isScanning = false;

    private ExecutorService orchestrator;

    private ExecutorService assetHandler;

    @Autowired
    private CandlesHandler candlesHandler;

    public ScannerMarket(){
        this.assetHandler = Executors.newFixedThreadPool(100);
    }

    @PostConstruct
    private void init(){
        this.start();
    }

    public void enableScanning(long chatId){
        this.chatId = chatId;
        this.isScanning = true;
    }

    public void disableScanning(){
        this.isScanning = false;
    }

    @Override
    public void run() {
        while(true){
            if(isScanning){
                System.out.println("Зашли в сканер!");
                List<Future<String>> list = new ArrayList<>(assetStorage.getListAssetStorage().size());
                for(String nameAsset : assetStorage.getListAssetStorage()){
                    list.add(assetHandler.submit(tasksGenerator.createTask(nameAsset)));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Отправили в обработку все!!!");

                List<String> resultList = new ArrayList<>(assetStorage.getListAssetStorage().size());
                for(Future<String> result : list){
                    String message = null;
                    try {
                        message = result.get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                    if(!message.equals("nothing")){
                        resultList.add(message);
                    }
                }
                System.out.println(resultList.isEmpty());
                if(!resultList.isEmpty()) {
                    for (String sendMessage : resultList) {
                        telegramScannerSender.sendMessage(chatId, sendMessage);
                    }
                }

            } else {

            }
        }
    }

}
