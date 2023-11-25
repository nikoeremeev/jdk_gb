package ru.example.seminar_3.task_4;

public class Idle implements Person {

    @Override
    public void doWork() {
        System.out.println(this.getClass().getName() + " i can't work");
    }

    @Override
    public void haveRest() {
        System.out.println(this.getClass().getName() + " Ready to rest, as always");
    }
    
}
