package com.sacrypto.test;

import jakarta.xml.bind.SchemaOutputResolver;

import java.util.concurrent.*;

public class CallableFactorial {

    public static int factorialResult;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Factorial factorial = new Factorial(5);
        Future<Integer> future = executorService.submit(factorial);
        try {
            factorialResult = future.get();
        } catch (Exception e) {
            System.out.println(e.getCause());
        } finally {
            executorService.shutdown();
        }
        System.out.println(factorialResult);


    }
}

class Factorial implements Callable<Integer>{
    int f;

    public Factorial(int f){
        this.f = f;
    }

    @Override
    public Integer call() throws Exception{
        if(f<=0){
            throw new Exception("Vi vveli nevernoe chislo");
        }
        return null;
    }
}