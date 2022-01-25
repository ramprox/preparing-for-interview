package ru.ramprox.ping_pong;

public class PingPong {

    private String message = "pong";
    private final Object lock = new Object();

    public void ping() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lock) {
                    while (!message.equals("pong")) {
                        lock.wait();
                    }
                    Thread.sleep(1000);
                    message = "ping";
                    System.out.println(message);
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pong() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (lock) {
                    while (!message.equals("ping")) {
                        lock.wait();
                    }
                    Thread.sleep(1000);
                    message = "pong";
                    System.out.println(message);
                    lock.notify();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
