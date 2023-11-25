package ru.example.hometask_3_1.hometask_3_1.src;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {

    public static void main(String[] args) {
        SQLiteHandler db = new SQLiteHandler("./Database/hometask.db");

        db.connect();

        Map<String, String> scheme = new HashMap<>();
        scheme.put("name", "TEXT");
        scheme.put("phone", "INT");

        db.deleteTable("users");
        db.createTable("users", scheme );

        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("name", "name#1");
        tempMap.put("phone", 123);
        db.writeTable("users", tempMap);

        tempMap = new HashMap<>();
        tempMap.put("name", "name#2");
        tempMap.put("phone", 1234);
        db.writeTable("users", tempMap);

        tempMap = new HashMap<>();
        tempMap.put("name", "name#3");
        tempMap.put("phone", 12345);
        db.writeTable("users", tempMap);


        List<Map<String, Object>> resultFull = db.readTable("users");
        for (Map<String,Object> map : resultFull) {
            db.printTableRow(map);
        }

        Map<String, Object> resultID = db.readTableByID("users", 3);
        db.printTableRow(resultID);

        tempMap = new HashMap<>();
        tempMap.put("name", "name#3updated");
        tempMap.put("phone", 123456);

        db.updateTable("users", tempMap, 3);

        resultID = db.readTableByID("users", 3);
        db.printTableRow(resultID);


        scheme = new HashMap<>();
        scheme.put("name", "TEXT");
        scheme.put("quantity", "INT");
        scheme.put("price", "REAL");

        db.deleteTable("stock");
        db.createTable("stock", scheme);

        tempMap = new HashMap<>();
        tempMap.put("name", "product#1");
        tempMap.put("quantity", 10);
        tempMap.put("price", 1.4);
        db.writeTable("stock", tempMap);

        tempMap = new HashMap<>();
        tempMap.put("name", "product#2");
        tempMap.put("quantity", 30);
        tempMap.put("price", 11.5);
        db.writeTable("stock", tempMap);

        tempMap = new HashMap<>();
        tempMap.put("name", "product#3");
        tempMap.put("quantity", 4);
        tempMap.put("price", 200.4);
        db.writeTable("stock", tempMap);


        db.disconnect();

    }

}