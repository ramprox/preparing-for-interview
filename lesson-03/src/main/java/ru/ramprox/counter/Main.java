package ru.ramprox.counter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter(0);
        int threadsCount = 100;
        Thread[] threads = new Thread[threadsCount];
        for(int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                for(int j = 0; j < 10000000; j++) {
                    counter.incrementAndGet();
                }
            });
        }
        for(int i = 0; i < threadsCount; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        System.out.println(counter.getCurrent());
    }
}
