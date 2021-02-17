package com.codecool.shop.model.log;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Log {

    private List<LogItem> logList;

    public Log() {
        logList = new ArrayList<>();
    }


    public void add(LogItem item){
        item.setId(logList.size()+1);
        logList.add(item);
    }


}
