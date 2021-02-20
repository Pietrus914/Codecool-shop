package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LogDao;
import com.codecool.shop.model.log.Log;
import com.codecool.shop.util.JsonWriter;

import java.util.ArrayList;
import java.util.List;

public class LogDaoMem implements LogDao {

    private static List<Log> logList = new ArrayList<>();
    private static LogDaoMem instance = null;

    public static LogDaoMem getInstance(){
        if (instance == null){
            instance = new LogDaoMem();
        }
        return instance;
    }

    private LogDaoMem(){}

    @Override
    public void add(Log log) {
        logList.add(log);
    }

    @Override
    public void save(Log log, String data) {
        JsonWriter.saveToFile(log, "logs/logHistoryForOrder", data);
    }


}
