package ru.example.seminar_3.task_4;

import java.util.Random;

public class Main{
    public static void main(String[] args) {
        Random rand = new Random();

        int quntity = rand.nextInt(10, 20);
        Person[] persons = new Person[quntity];

        for (int i = 0; i < quntity; i++) {
            if (rand.nextBoolean()){
                persons[i] = new Worker();
            } else {
                persons[i] = new Idle();
            }
        }

        System.out.println("------------Club -------------");
        Club<Person> club = new Club<>(persons);
        System.out.println("------------Workplace -------------");
        Workplace<Person> workplace = new Workplace<>(persons);
    }
}