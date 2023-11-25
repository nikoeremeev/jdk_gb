package ru.example.seminar_2.task_1;

public class ConsoleServerListener implements ServerListener {

    @Override
    public void generateMessage(String msg) {
        System.out.println(msg);
    }
    
}
