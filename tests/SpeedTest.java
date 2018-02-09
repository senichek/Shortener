package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;


import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SpeedTest {

    // Метод должен возвращать время в миллисекундах необходимое для получения идентификаторов
    // для всех строк из strings. Идентификаторы должны быть записаны в ids.
    public long getTimeForGettingIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date startTime = new Date();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        Date endTime = new Date();
        long runningTime = endTime.getTime() - startTime.getTime();

        return runningTime;
    }

    // Метод должен возвращать время в миллисекундах необходимое для получения строк для всех строк из ids.
    // Строки должны быть записаны в strings.
    public long getTimeForGettingStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startTime = new Date();
        for (Long x : ids) {
            strings.add(shortener.getString(x));
        }
        Date endTime = new Date();
        long runningTime = endTime.getTime() - startTime.getTime();

        return runningTime;
    }

    @Test
    public void testHashMapStorage(){
        /*
        * Создавать два объекта типа Shortener, один на базе HashMapStorageStrategy,
        * второй на базе HashBiMapStorageStrategy. Назовем их shortener1 и shortener2.
        * */
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        // Генерировать с помощью Helper 10000 строк и помещать их в сет со строками, назовем его origStrings.
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        /*
        * Получать время получения идентификаторов для origStrings (вызывать метод getTimeForGettingIds для shortener1,
        * а затем для shortener2).
        * */
        Set<Long> testIdsForShortener1 = new HashSet<>();
        Set<Long> testIdsForShortener2 = new HashSet<>();
        Long timeForShortenerOne = getTimeForGettingIds(shortener1, origStrings, testIdsForShortener1);
        Long timeForShortenerTwo = getTimeForGettingIds(shortener2, origStrings, testIdsForShortener2);
        // Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 больше, чем для shortener2
        Assert.assertTrue(timeForShortenerOne > timeForShortenerTwo);

        // Получать время получения строк (вызывать метод getTimeForGettingStrings для shortener1 и shortener2).
        Set<Long> setOfIdsForGettingStrings = new HashSet<>();
        Set<String> setOfStringsforGettingStrings = new HashSet<>();
        for (String s : origStrings) {
            setOfIdsForGettingStrings.add(shortener1.getId(s));
        }
        timeForShortenerOne = getTimeForGettingStrings(shortener1, setOfIdsForGettingStrings, setOfStringsforGettingStrings);
        timeForShortenerTwo = getTimeForGettingStrings(shortener2, setOfIdsForGettingStrings, setOfStringsforGettingStrings);

        /*
        * Проверять с помощью junit, что время, полученное в предыдущем пункте для shortener1 примерно равно времени для shortener2.
        * Используй метод assertEquals(float expected, float actual, float delta). В качестве delta можно использовать 30
        * */
        Assert.assertEquals(timeForShortenerOne, timeForShortenerTwo, 30);




    }
}
