package ru.ramprox.counter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private Lock lock = new ReentrantLock();
    private volatile int count;

    public Counter(int initialCount) {
        this.count = initialCount;
    }

    public int incrementAndGet() {
        lock.lock();
        count++;
        lock.unlock();
        return count;
    }

    public int decrementAndGet() {
        lock.lock();
        count--;
        lock.unlock();
        return count;
    }

    public int getCurrent() {
        return count;
    }
}
