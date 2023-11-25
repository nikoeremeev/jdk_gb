package ru.example.seminar_3.task_3;

public class Main{
    public static void main(String[] args) {
        GenericIterator<Integer> genericIterator = new GenericIterator<>(new Integer[]{1,2,3,4});

        for (Integer item : genericIterator) {
            System.out.println(item);
        }

        for (Integer item : genericIterator) {
            System.out.println(item);
        }
    }
}