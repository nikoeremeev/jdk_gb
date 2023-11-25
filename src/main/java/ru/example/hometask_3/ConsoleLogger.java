package ru.example.hometask_3;

import java.time.LocalDateTime;

public class ConsoleLogger implements ImyConnectLogger {

    @Override
    public void print(String msg) {
        System.out.println(LocalDateTime.now() + " : " +  msg);
    }
    
}
