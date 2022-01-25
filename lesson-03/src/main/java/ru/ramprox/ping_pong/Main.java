package ru.ramprox.ping_pong;

public class Main {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        new Thread(pingPong::ping).start();
        new Thread(pingPong::pong).start();
    }
}
