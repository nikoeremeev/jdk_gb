package ru.example.seminar_3.task_4;

public class Club<T extends Person> {
    T[] arr;

    Club(T[] arr){
        this.arr = arr;

        for (T person : arr) {
            person.haveRest();
        }
    }

}