package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Example4 {
    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> factorialThreadList = new ArrayList<>();

        for(long inputNumber : inputNumbers){
            factorialThreadList.add(new FactorialThread(inputNumber));
        }

        for(Thread thread : factorialThreadList){
            thread.start();
        }

        for(int i = 0; i < inputNumbers.size(); i++){
            FactorialThread factorialThread = factorialThreadList.get(i);

            if(factorialThread.isFinished){
                System.out.println("Factorial of " + inputNumbers.get(i) + " is " + factorialThread.getResult());
            }else {
                System.out.println("The calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

    private static class FactorialThread extends Thread{
        private final long inputNumber;
        private BigInteger result = BigInteger.ZERO;
        private boolean isFinished = false;

        public FactorialThread(long inputNumber){
            this.inputNumber = inputNumber;
        }

        @Override
        public void run(){
            this.result = factorial(inputNumber);
            this.isFinished = true;
        }

        public BigInteger factorial(long n){
            BigInteger tempResult = BigInteger.ONE;
            for(long i = n; i > 0; i--){
                tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
            }
            return tempResult;
        }

        public boolean isFinished(){
            return this.isFinished;
        }

        public BigInteger getResult(){
            return this.result;
        }
    }
}