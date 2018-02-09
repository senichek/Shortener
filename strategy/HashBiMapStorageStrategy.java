package com.javarush.task.task33.task3310.strategy;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class HashBiMapStorageStrategy implements StorageStrategy {

    HashBiMap<Long, String> data = HashBiMap.create();

    @Override
    public boolean containsKey(Long key) {
        if (data.containsKey(key)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        if (data.containsValue(value)) {
            return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);

    }

    @Override
    public Long getKey(String value) {
        // Метод getKey должен возвращать значение полученное из data.inverse()
        BiMap<String, Long> reverseMap = data.inverse();

        return reverseMap.get(value); // Value в данном случае это key (ключ)
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
