package ru.example.lection_5;

public class MyRunnable implements Runnable{
    
    private String value;
    private Object monitor;

    MyRunnable(String arg){
        value = arg;
        this.monitor = MyRunnable.class;
    }


    static void print(String arg){
        System.out.print(arg);
    } 

    @Override
    public void run() {
        while (true){
            synchronized (monitor){
                print(value);
                try {
                    Thread.sleep(1000);
                    monitor.notify();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
}
