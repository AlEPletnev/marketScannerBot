package com.sacrypto.test;


import jakarta.xml.bind.SchemaOutputResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableFactorial2 {

    public static int[] factorialResult;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Factorial factorial = new Factorial(5);
//        Future<Integer> future = executorService.submit(factorial);
//        try {
//            factorialResult = future.get();
//        } catch (Exception e) {
//            System.out.println(e.getCause());
//        } finally {
//            executorService.shutdown();
//        }
//        System.out.println(factorialResult);
//
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Factorial2> factorial = new ArrayList<>();
        for(int i = 1; i <= 10; i++){
            factorial.add(new Factorial2(i));
        }
        List<Future<Integer>> resultList = new ArrayList<>();
        for(Factorial2 factorial2 : factorial){
            resultList.add(executorService.submit(factorial2));
        }

        for(Future<Integer> future : resultList){
            System.out.println(future.get());
        }
    }
}

class Factorial2 implements Callable<Integer>{
    int f;

    public Factorial2(int f){
        this.f = f;
    }

    @Override
    public Integer call() throws Exception{
        return f;
    }
}