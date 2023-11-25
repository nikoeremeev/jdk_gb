package ru.example.seminar_2.task_4;

public class FrontEndDeveloper extends Developer {

    @Override
    void drinkCoffee() {
        System.out.println("drinkCoffee");
    }

    @Override
    void smoke() {
        System.out.println("smoke");
    }


    public void developGUI() {
        System.out.println("create GUI");
    }
    

}
