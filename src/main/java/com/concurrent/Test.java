package com.concurrent;

public class Test {
    private volatile static int times = 0;
    public static void main(String[] args) {
        Mutex mutex = new Mutex();

        for (int i = 0; i < 1000; i++) {
            Thread th =  new Thread(() -> {
                //使用自定义锁
                mutex.lock();
                System.out.println(times++);
                //释放锁
                mutex.unlock();
            });
            th.start();
        }
    }
}
