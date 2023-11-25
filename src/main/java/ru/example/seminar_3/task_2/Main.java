package ru.example.seminar_3.task_2;

import java.awt.Color;

public class Main{
    public static void main(String[] args) {
        System.out.println("--------------- Integer -----------");
        GenericArray<Integer> arrayInteger = new GenericArray<>();
        System.out.println("--------------- before-----------");
        System.out.println(arrayInteger.info());
        arrayInteger.add(27647);
        arrayInteger.add(700);
        arrayInteger.add(32000);
        System.out.println("--------------- after add-----------");
        System.out.println(arrayInteger.info());
        arrayInteger.remove(32000);
        System.out.println("--------------- after remove-----------");
        System.out.println(arrayInteger.info());

        System.out.println("--------------- String -----------");
        GenericArray<String> stringInteger = new GenericArray<>();
        System.out.println("--------------- before-----------");
        System.out.println(stringInteger.info());
        stringInteger.add("one");
        stringInteger.add("two");
        stringInteger.add("three");
        System.out.println("--------------- after add-----------");
        System.out.println(stringInteger.info());
        stringInteger.remove("two");
        System.out.println("--------------- after remove-----------");
        System.out.println(stringInteger.info());

    }
}