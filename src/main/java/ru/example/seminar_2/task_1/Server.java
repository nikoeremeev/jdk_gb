package ru.example.seminar_2.task_1;

public class Server  {
    private boolean status; //0=offline, 1=worked
    private ServerListener serverListener;

    Server(ServerListener serverListener){
        this.status = false;
        this.serverListener = serverListener;
    }

    public void start() {
        String msg;
        if (status) {
            msg = "Server is already running.";
        } else {
            status = true;
            msg = "Server started.";
        }
        serverListener.generateMessage(msg);
    }

    public void stop(){
        String msg;
        if (status) {
            status = false;
            msg = "Server stopped.";
        } else {
            msg = "Server is already stopped.";
        }
        serverListener.generateMessage(msg);
    }
    
}
