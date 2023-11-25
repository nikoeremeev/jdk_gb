package ru.example.hometask_3_1.hometask_3_1.src;

import java.util.List;
import java.util.Map;

public interface ImyDBhandler {

    void connect();
    void disconnect();
    void createTable(String name, Map<String, String> scheme);
    void deleteTable(String name);
    void writeTable(String name, Map<String, Object> value);
    void updateTable(String name, Map<String, Object> value, int id);
    List<Map<String, Object>> readTable(String tablename);
    Map<String, Object> readTableByID(String tablename, int id);

}
