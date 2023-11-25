package ru.example.seminar_2.task_2;

import java.util.Random;

public class Server implements ServerListener {
    private Random rand = new Random();
    private boolean status; //0=offline, 1=worked
    private BaseListener serverListener;

    Server(BaseListener serverListener){
        this.status = false;
        this.serverListener = serverListener;
    }

    public void start() {
        String msg;
        if (rand.nextBoolean() | rand.nextBoolean()){

            if (status) {
                msg = "Server is already running.";
            } else {
                status = true;
                msg = "Server started.";
            }

        } else {
            msg = "Server not respond";
        }
        serverListener.generateMessage(msg);

    }

    public void stop(){
        String msg;
        if (rand.nextBoolean() | rand.nextBoolean()){

            if (status) {
                status = false;
                msg = "Server stopped.";
            } else {
            msg = "Server is already stopped.";
            }

        } else {
            msg = "Server not respond";
        }
        serverListener.generateMessage(msg);
    }
    
}
