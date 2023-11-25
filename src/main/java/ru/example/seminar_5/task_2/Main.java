package ru.example.seminar_5.task_2;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{

    public static void main(String[] args) {
            AtomicBoolean switcher = new AtomicBoolean(true);
            AtomicInteger a = new AtomicInteger(100);
            Object x = new Object();


            Thread threadA = new Thread(() -> {
                while (a.get() > 0) {
                    synchronized (x) {
                        try {
                            Thread.sleep(1000);
                            switcher.set(!switcher.get());
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }



            });
    
            Thread threadB = new Thread(() -> {
                while (a.get() > 0) {
                    synchronized (x) {
                            if (!switcher.get()) {
                                for (int i = a.get(); i >= 0; i--) {
                                    if (switcher.get()) {
                                        System.out.print(i);
                                        a.set(i);
                                        break;
                                    }
                                }
                            }

                        }

                    }
            });

            threadA.start();
            threadB.start();
        }
}