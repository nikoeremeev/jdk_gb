package ru.example.hometask_5.task_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LogHandler {
    private File logFile;
    private static AtomicBoolean changeFlag = new AtomicBoolean();

    LogHandler(String fileName) throws IOException{
        logFile = new File(fileName);
        if (! logFile.exists()) {
            this.logFile.createNewFile();
        }
    }

    public List<String> Read() throws IOException{
        BufferedReader fileStream = new BufferedReader(new FileReader(this.logFile));
        List<String> lines = (fileStream.lines().toList());
        fileStream.close();
        return lines;
    }

    public void Write(String argLine) throws IOException{
        BufferedReader tempReader = new BufferedReader(new FileReader(this.logFile));
        List<String> lines = (tempReader.lines().toList());
        BufferedWriter fileStream = new BufferedWriter(new FileWriter(this.logFile));
        for (String line : lines) {
            fileStream.append(line);
            fileStream.newLine();
        }
        fileStream.write(argLine);

        //fileStream.newLine();
        fileStream.flush();
        fileStream.close();
        tempReader.close();
        setDataChanged(true);
    }

    public void setDataChanged(boolean arg){
        changeFlag.set(arg);
    }

    public boolean dataChanged(){
        return changeFlag.get();
    }
}
