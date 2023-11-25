package ru.example.lection_5;

public class Main{
    static int count1 = 0;
    static int count2 = 0;
    

    public static void main(String[] args) throws InterruptedException {


        Thread a = new Thread(new MyRunnable("1"));
        Thread b = new Thread(new MyRunnable("2"));
        Thread c = new Thread(new MyRunnable("3"));
        a.start();
        b.start();
        c.start(); 
        
    }
}