package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class OurHashBiMapStorageStrategy implements StorageStrategy {

    HashMap<Long, String> k2v = new HashMap<>();
    HashMap<String, Long> v2k = new HashMap<>();

// Метод containsKey должен проверять содержится ли полученный параметр в k2v.
    @Override
    public boolean containsKey(Long key) {
        if (k2v.containsKey(key)) {
            return true;
        }
        return false;
    }
// Метод containsValue должен проверять содержится ли полученный параметр в v2k.
    @Override
    public boolean containsValue(String value) {
        if (v2k.containsKey(value)) {
            return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key, value);
        v2k.put(value, key);
    }
// Метод getKey должен возвращать значение полученное из v2k.
    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
