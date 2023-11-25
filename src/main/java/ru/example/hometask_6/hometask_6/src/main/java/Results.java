package ru.example.hometask_6.hometask_6.src.main.java;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;
import java.util.Map.*;


public class Results {

    private File logFile;
    private HashMap<Integer, AbstractMap.SimpleEntry<Boolean,Boolean>> result;

    public double getLoyalWinRate() {
        return loyalWinRate;
    }

    public double getTraitorWinRate() {
        return traitorWinRate;
    }

    private double loyalWinRate;
    private double traitorWinRate;
    Results(String fileName) throws IOException {
        result = new HashMap<>();
        logFile = new File(fileName);
        if (! logFile.exists()) {
            this.logFile.createNewFile();
        }
        unpackData();
        analyse();
    }

    private void analyse(){
        int loyalCount = 0;
        int traitorCount = 0;
        for (int i = 0; i < result.size(); i++) {
            AbstractMap.SimpleEntry<Boolean, Boolean> tempRes = result.get(i);
            if (tempRes.getKey() && tempRes.getValue()){ //изменил результат и победа в игре
                traitorCount++;
            };
            if (!tempRes.getKey() && tempRes.getValue()){ //не изменил результат и победа в игре
                loyalCount++;
            };
        }
        loyalWinRate = Math.round(((double)loyalCount) / ((double) result.size()) * 100.0);
        traitorWinRate = Math.round(((double)traitorCount) / ((double) result.size()) * 100.0);
    }
    public HashMap<Integer,  AbstractMap.SimpleEntry<Boolean,Boolean>> getResult() {
        return result;
    }

    public void setResult(Integer index, AbstractMap.SimpleEntry<Boolean,Boolean> value){
        result.put(index, value);
        packData(index, result.get(index));
        analyse();
    }

    public void unpackData(){
        try {
            List<String> rawData = Read();
            if (! rawData.isEmpty()){
                result = new HashMap<>();
                for (String raw: rawData) {
                    String[] rawElements = raw.split(";");
                    if (rawElements.length == 3){
                        result.put(Integer.valueOf(rawElements[0]),
                                new AbstractMap.SimpleEntry<>(Boolean.valueOf(rawElements[1]), Boolean.valueOf(rawElements[2])));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка распаковки данных о исследовании");
        }
    }

    public void packData(Integer key,  AbstractMap.SimpleEntry<Boolean,Boolean> entry){
        String rawData = "";
        rawData = "" + key + ";" + entry.getKey() + ";" + entry.getValue() ;
        try {
            Write(rawData);
        } catch (IOException e) {
            System.out.println("Ошибка упаковки данных о исследовании");
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
        fileStream.newLine();
        fileStream.flush();
        fileStream.close();
        tempReader.close();
    }
}
