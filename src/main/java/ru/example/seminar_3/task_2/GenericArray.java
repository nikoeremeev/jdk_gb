package ru.example.seminar_3.task_2;

import java.util.Arrays;

public class GenericArray<T> {
    Object[] arr;

    GenericArray(){
       arr = new Object[0]; 
    }


    public void add(T t){
        Object[] temp_arr = new Object[arr.length + 1];
        
        for (int index = 0; index < arr.length; index++) {
            temp_arr[index] = arr[index];
        }
        temp_arr[temp_arr.length - 1] = t;
        
        arr = temp_arr;
        // arr = new Object[temp_arr.length];
        // for (int index = 0; index < temp_arr.length; index++) {
        //     arr[index] = temp_arr[index];
        // }
    }

    public void remove(T t){
        Integer position = find(t);
        if (position != null) {
            Object[] temp_arr = new Object[arr.length - 1];
            
            for (int i = 0; i < position; i++) {
                temp_arr[i] = arr[i];
            }

            for (int i = position; i < arr.length -1; i++) {
                temp_arr[i] = arr[i + 1];
            }

            arr = temp_arr;
            // arr = new Object[temp_arr.length];
            // for (int index = 0; index < temp_arr.length; index++) {
            //     arr[index] = temp_arr[index];
            // }
        }

    }

    public Integer find(T t){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(t)) return i;
        }
        return null;
    }

    public String info(){
        return Arrays.toString(arr);
    }



}


