package org.example;

import java.math.BigInteger;

public class Example3 {
    //interrupt() metodu, Java'da çalışan bir threadı kesmek veya durdurmak için kullanılan bir mekanizmadır.
    //Bloklanan Thread, bloklanan thread uyandırılır ve InterruptedException throw etmesıne neden olunur ve threadın bloklandığı yerden çıkması sağlanır.
    //Uzun süren işlemlerin durdurulması içinde kullanılır.
    //interrupt edilen thread içerisinde kontrol işleminin olması gerekir aksi halde thread interrupt edilemez.

    //DeamondThread, Daemon thread'ler genellikle arka plan görevleri veya bakım işlemleri için kullanılır.
    //JVM, yalnızca kullanıcı thread'leri (non-daemon thread'ler) tamamlandığında sona erer.
    //Eğer sadece daemon thread'ler çalışıyorsa, JVM tüm daemon thread'leri durdurur ve programı sonlandırır.
    public static void main(String[] args) {
        Thread thread1 = new Thread(new BlockingThread());
        Thread thread2 = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("300000000")));

        thread2.setDaemon(true);
        thread2.start();
        thread2.interrupt();
    }

    private static class BlockingThread implements Runnable {
        @Override
        public void run(){
            try{
                Thread.sleep(500000);
            }catch (InterruptedException exception){
                System.out.println("Existing blocking thread");
            }
        }
    }
    private static class LongComputationTask implements Runnable{
        private final BigInteger base;
        private final BigInteger power;
        public LongComputationTask(BigInteger base, BigInteger power){
            this.base = base;
            this.power = power;
        }
        @Override
        public void run(){
            System.out.println(base + " ^ " + power + " = " + pow(base, power));
        }

        private BigInteger pow(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;

            for(BigInteger i = BigInteger.ZERO; i.compareTo(power) != 0; i = i.add(BigInteger.ONE)){
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("This metod is interrupted");
                    return BigInteger.ZERO;
                }
                result = result.multiply(base);
            }

            return result;
        }
    }
}