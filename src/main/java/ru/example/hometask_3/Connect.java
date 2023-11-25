package ru.example.hometask_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Connect {
    static ConsoleLogger logger = new ConsoleLogger();
    static Connection conn = null;
    static String database;
    static Map<String, Map<String, String>> schemes = new HashMap<String, Map<String, String>>();
    static ResultSet result;

    //установить соединение
    public static void connect(String argDatabase) {
        try {
            // db параметры
            String url = String.format("jdbc:sqlite:%s", argDatabase);
            // создание подключения к db
            conn = DriverManager.getConnection(url);
            database = argDatabase;
            logger.print(String.format("База данных %s: связь была установлена", database));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        } 
    }

    //разорвать соединение
    public static void disconnect() {
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
	public static void createTable(String tablename, Map<String, String> scheme)
	{   
        try{
            StringBuilder builder = new StringBuilder();
            //'name' text, 'phone' INT);
            builder.append(String.format("CREATE TABLE if not exists '%s' ('id' INTEGER PRIMARY KEY AUTOINCREMENT ", tablename));
            for (String key : scheme.keySet()) {
                String parName = key;
                String parType = scheme.get(key);
                builder.append(String.format(", '%s' %s", parName, parType));
            }
            builder.append(");");
            conn.createStatement().execute(builder.toString());
            schemes.put(tablename, scheme);
            logger.print(String.format("База данных %s: Таблица %s была создана", database, tablename));
        } catch (SQLException e) {
            logger.print(e.getMessage());
        } 

	}
	//удаление таблицы
	public static void deleteTable(String tablename)
	{
        try {
            conn.createStatement().execute(String.format("DROP TABLE if exists '%s';", tablename));
            logger.print(String.format("База данных %s: Таблица %s была удалена", database, tablename));
            schemes.remove(tablename);
        } catch (SQLException e) {
            logger.print(e.getMessage());
        } 
	}

    public static void writeTable(String tablename, Map<String, Object> value) {
            StringBuilder builder = new StringBuilder();
            String cmd = "";
            Map<String, String> scheme = schemes.get(tablename);
            if (value.size()!=0) {
                builder.append(String.format("INSERT INTO '%s' (", tablename));
                for (String key : value.keySet()) {
                    builder.append(String.format("'%s',", key));
                }
                builder.append(")");
                builder.append(String.format(" VALUES (")); 
                for (String key : value.keySet()) {
                    String type = scheme.get(key);
                    if (type.toLowerCase().equals("text")) {
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
                logger.print(String.format("База данных %s: Таблица %s вставлена запись", database, tablename));
            } catch (SQLException e) {
                logger.print(e.getMessage());
            }
    }

    public static void updateTable(String tablename, Map<String, Object> value, int id) {
            StringBuilder builder = new StringBuilder();
            String cmd = "";
            Map<String, String> scheme = schemes.get(tablename);
            if (value.size()!=0) {
                builder.append(String.format("UPDATE '%s' SET ", tablename));
                for (String key : value.keySet()) {
                    String type = scheme.get(key); 
                    if (type.toLowerCase().equals("text")) {
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
                logger.print(String.format("База данных %s: Таблица %s обновлена запись %s", database, tablename, id));
            } catch (SQLException e) {
                logger.print(e.getMessage());
            }
    }

    public static List<Map<String, Object>> readTable(String tablename){
        List<Map<String, Object>> retValue = new LinkedList<Map<String, Object>>(); 
        Map<String, Object> tempMap = new HashMap<String, Object>();
        Map<String, String> scheme = schemes.get(tablename);
        try {
            result = conn.createStatement().executeQuery(String.format("SELECT * FROM '%s';", tablename));
            while (result.next()){
                tempMap = new HashMap<String, Object>();
                for (String key : scheme.keySet()) {
                    String parName = key;
                    String parType = scheme.get(key);
                    Object parValue = null;
                    switch (parType.toLowerCase()) {
                        case "text":
                            parValue = result.getString(parName);
                            break;
                        case "int":
                            parValue = result.getInt(parName);
                            break;
                        case "time":
                            parValue = result.getTime(parName);
                            break;
                        case "real":
                            parValue = result.getDouble(parName);
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

    public static Map<String, Object> readTableByID(String tablename, int id){
        Map<String, Object> retValue = new HashMap<String, Object>();
        Map<String, String> scheme = schemes.get(tablename);
        try {
            result = conn.createStatement().executeQuery(String.format("SELECT * FROM '%s' WHERE id=%d;", tablename, id));
            for (String key : scheme.keySet()) {
                String parName = key;
                String parType = scheme.get(key);
                Object parValue = null;
                switch (parType.toLowerCase()) {
                    case "text":
                        parValue = result.getString(parName);
                        break;
                    case "int":
                        parValue = result.getInt(parName);
                        break;
                    case "time":
                        parValue = result.getTime(parName);
                        break;
                    case "real":
                        parValue = result.getDouble(parName);
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

    public static void printTableRow(Map<String, Object> row) {
        for (Entry<String, Object> item : row.entrySet()) {
            logger.print("key: " + item.getKey() + " value: " + item.getValue());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        connect("./Database/hometask.db");
        
        Map<String, String> scheme = new HashMap<String, String>();
        scheme.put("name", "TEXT");
        scheme.put("phone", "INT");
        
        deleteTable("test");
        createTable("test", scheme );
        
        Map<String,Object> tempMap = new HashMap<String,Object>();
        tempMap.put("name", "name#1");
        tempMap.put("phone", 123);
        writeTable("test", tempMap);
        
        tempMap = new HashMap<String,Object>();
        tempMap.put("name", "name#2");
        tempMap.put("phone", 1234);
        writeTable("test", tempMap);
        
        tempMap = new HashMap<String,Object>();
        tempMap.put("name", "name#3");
        tempMap.put("phone", 12345);
        writeTable("test", tempMap);


        List<Map<String, Object>> resultFull = readTable("test");
        for (Map<String,Object> map : resultFull) {
            printTableRow(map);
        }

        Map<String, Object> resultID = readTableByID("test", 3);
        printTableRow(resultID);

        tempMap = new HashMap<String,Object>();
        tempMap.put("name", "name#3updated");
        tempMap.put("phone", 123456);

        updateTable("test", tempMap, 3);
        
        resultID = readTableByID("test", 3);
        printTableRow(resultID);
        disconnect();
    }
}