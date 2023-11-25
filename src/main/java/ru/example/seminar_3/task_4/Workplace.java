package ru.example.seminar_3.task_4;

public class Workplace<T extends Person> {
    T[] arr;

    Workplace(T[] arr){
        this.arr = arr;

        for (T person : arr) {
            person.doWork();
        }
    }

}
