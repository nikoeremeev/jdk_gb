package ru.example.hometask_6.hometask_6.src.main.java;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Game {
    Logic logic;
    Results results;
    UI ui;
    public void game(){
        logic = new Logic();
        try {
            results = new Results("results");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logic.roll();
        ui = new UI(this, logic, results);
    }

    public void restart(){
        logic = new Logic();
        logic.roll();
        ui.dispose();
        ui = new UI(this, logic, results);
    }
}
