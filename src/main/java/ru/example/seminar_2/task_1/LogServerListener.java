package ru.example.seminar_2.task_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class LogServerListener implements ServerListener {

    private File logFile;

    LogServerListener(String filename) throws IOException {
        logFile = new File(filename);
        if (! logFile.exists()){
            logFile.createNewFile();

        }
    }

    @Override
    public void generateMessage(String msg) {
        msg =  LocalDateTime.now().toString() + " "  + msg;
        try (BufferedReader tempReader = new BufferedReader(new FileReader(this.logFile))) {
            List<String> lines = (tempReader.lines().toList());
            BufferedWriter fileStream = new BufferedWriter(new FileWriter(this.logFile));
            for (String line : lines) {
                fileStream.append(line);
                fileStream.newLine();
            }
            fileStream.write(msg);
            fileStream.newLine();
            fileStream.flush();
            fileStream.close();
            tempReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
