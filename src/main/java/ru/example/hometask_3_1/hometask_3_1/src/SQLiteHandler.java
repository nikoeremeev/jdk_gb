package ru.example.hometask_3_1.hometask_3_1.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SQLiteHandler implements ImyDBhandler {
    ConsoleLogger logger;
    Connection conn;
    String database;
    Map<String, Map<String, String>> schemes;
    ResultSet result;

    public SQLiteHandler(String database){
        this.database = database;
        this.schemes = new HashMap<>();
        this.conn = null;
        this.logger = new ConsoleLogger();
    }

    //установить соединение
    public void connect() {
        try {
            // db параметры
            String url = String.format("jdbc:sqlite:%s", database);
            // создание подключения к db
            conn = DriverManager.getConnection(url);
            logger.print(String.format("База данных %s: связь была установлена", database));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
    }

    //разорвать соединение
    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
                logger.print(String.format("База данных %s: связь была разорвана", database));
            }
        } catch (SQLException ex) {
            logger.print(ex.getMessage());
        }
    }

    //создание таблицы
    public void createTable(String name, Map<String, String> scheme)
    {
        try{
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("CREATE TABLE if not exists '%s' ('id' INTEGER PRIMARY KEY AUTOINCREMENT ", name));
            for (String key : scheme.keySet()) {
                builder.append(String.format(", '%s' %s", key, scheme.get(key)));
            }
            builder.append(");");
            conn.createStatement().execute(builder.toString());
            schemes.put(name, scheme);
            logger.print(String.format("База данных %s: Таблица %s была создана", database, name));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }

    }
    //удаление таблицы
    public void deleteTable(String name)
    {
        try {
            conn.createStatement().execute(String.format("DROP TABLE if exists '%s';", name));
            logger.print(String.format("База данных %s: Таблица %s была удалена", database, name));
            schemes.remove(name);
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
    }

    public void writeTable(String name, Map<String, Object> value) {
        StringBuilder builder = new StringBuilder();
        String cmd;
        Map<String, String> scheme = schemes.get(name);
        if (!value.isEmpty()) {
            builder.append(String.format("INSERT INTO '%s' (", name));
            for (String key : value.keySet()) {
                builder.append(String.format("'%s',", key));
            }
            builder.append(")");
            builder.append(" VALUES (");
            for (String key : value.keySet()) {
                String type = scheme.get(key);
                if (type.equalsIgnoreCase("text")) {
                    builder.append(String.format("\"%s\",", value.get(key)));
                } else {
                    builder.append(String.format("%s,", value.get(key)));
                }

            }
            builder.append(");");
        }
        try {
            cmd = builder.toString();
            cmd = cmd.replace("(,", "(");
            cmd = cmd.replace(",)", ")");
            cmd = cmd.replace(";;", ";");
            conn.createStatement().execute(cmd);
            logger.print(String.format("База данных %s: Таблица %s вставлена запись", database, name));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
    }

    public void updateTable(String name, Map<String, Object> value, int id) {
        StringBuilder builder = new StringBuilder();
        String cmd;
        Map<String, String> scheme = schemes.get(name);
        if (!value.isEmpty()) {
            builder.append(String.format("UPDATE '%s' SET ", name));
            for (String key : value.keySet()) {
                String type = scheme.get(key);
                if (type.equalsIgnoreCase("text")) {
                    builder.append(String.format("'%s' = \"%s\",", key, value.get(key)));
                } else {
                    builder.append(String.format("'%s' = %s,", key, value.get(key)));
                }
            }
            builder.append(String.format(" WHERE Id=%d;", id));
        }
        try {
            cmd = builder.toString();
            cmd = cmd.replace(", WHERE", " WHERE");
            conn.createStatement().execute(cmd);
            logger.print(String.format("База данных %s: Таблица %s обновлена запись %s", database, name, id));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
    }

    public List<Map<String, Object>> readTable(String tablename){
        List<Map<String, Object>> retValue = new LinkedList<>();
        Map<String, Object> tempMap;
        Map<String, String> scheme = schemes.get(tablename);
        try {
            result = conn.createStatement().executeQuery(String.format("SELECT * FROM '%s';", tablename));
            while (result.next()){
                tempMap = new HashMap<>();
                for (String key : scheme.keySet()) {
                    String parType = scheme.get(key);
                    Object parValue = null;
                    switch (parType.toLowerCase()) {
                        case "text":
                            parValue = result.getString(key);
                            break;
                        case "int":
                            parValue = result.getInt(key);
                            break;
                        case "time":
                            parValue = result.getTime(key);
                            break;
                        case "real":
                            parValue = result.getDouble(key);
                            break;
                        default:
                            break;
                    }
                    tempMap.put(key, parValue);
                }
                retValue.add(tempMap);
            }
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
        logger.print(String.format("База данных %s: Таблица %s было прочитано %d записи", database, tablename, retValue.size()));
        return retValue;
    }

    public Map<String, Object> readTableByID(String tablename, int id){
        Map<String, Object> retValue = new HashMap<>();
        Map<String, String> scheme = schemes.get(tablename);
        try {
            result = conn.createStatement().executeQuery(String.format("SELECT * FROM '%s' WHERE id=%d;", tablename, id));
            for (String key : scheme.keySet()) {
                String parType = scheme.get(key);
                Object parValue = null;
                switch (parType.toLowerCase()) {
                    case "text":
                        parValue = result.getString(key);
                        break;
                    case "int":
                        parValue = result.getInt(key);
                        break;
                    case "time":
                        parValue = result.getTime(key);
                        break;
                    case "real":
                        parValue = result.getDouble(key);
                        break;
                    default:
                        break;
                }
                retValue.put(key, parValue);
            }
        } catch (SQLException e) {
            logger.print(e.getMessage());
        }
        logger.print(String.format("База данных %s: Таблица %s было прочитана запись %d ", database, tablename, id));
        return retValue;
    }

    public void printTableRow(Map<String, Object> row) {
        for (Entry<String, Object> item : row.entrySet()) {
            logger.print("key: " + item.getKey() + " value: " + item.getValue());
        }
    }
}