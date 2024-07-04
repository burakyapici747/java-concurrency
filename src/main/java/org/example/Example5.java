package org.example;

import java.math.BigInteger;

public class Example5 {
    //Thread.join() metodu, Java'da bir thread'in diğer bir thread'in tamamlanmasını beklemesini sağlamak için kullanilir.
    public static void main(String[] args) {
        BigInteger result;
        PowerCalculatingThread powerCalculatingThread1 = new PowerCalculatingThread(BigInteger.valueOf(5), BigInteger.valueOf(10L));
        PowerCalculatingThread powerCalculatingThread2 = new PowerCalculatingThread(BigInteger.valueOf(5), BigInteger.valueOf(10L));
        powerCalculatingThread1.start();
        powerCalculatingThread2.start();

        try {
            powerCalculatingThread1.join();
            powerCalculatingThread2.join();
        }catch (InterruptedException e){
            System.out.println("Hata var : " + e.getMessage());
        }

        result = powerCalculatingThread1.getResult().add(powerCalculatingThread2.getResult());

        System.out.printf(result.toString());
    }
    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            for(BigInteger i = BigInteger.ZERO;
                i.compareTo(power) !=0;
                i = i.add(BigInteger.ONE)) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() { return result; }
    }
}
