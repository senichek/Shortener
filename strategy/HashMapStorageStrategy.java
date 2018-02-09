package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;


public class HashMapStorageStrategy implements StorageStrategy {

    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        for (Map.Entry entry : data.entrySet()) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        for (Map.Entry entry : data.entrySet()) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        for (Map.Entry<Long, String> e : data.entrySet()) {
            if (data.containsValue(value)) {
                return e.getKey();
            }
        }
        
        return null;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
