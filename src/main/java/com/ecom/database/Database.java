package com.ecom.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Database implements Serializable {

    private List<Object> data = new ArrayList<>();
    private static Database dbInstance;

    private Database() {
    }

    public static Database getDbInstance() {
        if (dbInstance == null) {
            dbInstance = new Database();

        }

        return dbInstance;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }
    public List<Object> getData(Class<?> clazz) {
            return data
            .stream()
            .filter(clazz::isInstance)
            .collect(Collectors.toList());
        }


}
