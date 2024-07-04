package org.example;

public class Example1 {
    //setUncaughtExceptionHandler
    //Thread içerisinde oluşabilecek RuntimeException hatalarının handle edilememesi gibi durumlarda
    //Thread içerisinde oluşan hatayı handle ederek progamın akışının kesilmesini engeller.
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("We are now in thread " + Thread.currentThread().getName());
                System.out.println("Current thread priority is " + Thread.currentThread().getPriority());
                throw new RuntimeException("RuntimeException");
            }
        });

        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread: " + t.getName() + " the error is " + e.getMessage());
            }
        });

        thread1.setName("Uzayli Thread");
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " Before starting a new thread");
        thread1.start();
        System.out.println("We are in thread: " + Thread.currentThread().getName() + " After starting a new thread");
    }
}