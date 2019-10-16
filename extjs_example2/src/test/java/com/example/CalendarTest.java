package com.example;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by DINGJUN on 2019.10.16.
 */
public class CalendarTest {
    Calendar calendar;
    @Before
    public void before() {
        calendar = Calendar.getInstance();
        calendar.set(2019,9,13);
    }
    @Test
    public void test1() {
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
    }

    @Test
    public void test2() {
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void test3() {
        int n = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 西方的周一是指的周日
        if (n == 0) {
            n = 7;
        }
        calendar.add(Calendar.DATE, -(7 + (n - 1)));// 上周一的日期
        calendar.add(Calendar.DAY_OF_WEEK, 0);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }

    @Test
    public void test4() {
        int n = calendar.get(Calendar.DAY_OF_WEEK) - 1; // 西方的周一是指的周日
        if (n == 0) {
            n = 7;
        }
        calendar.add(Calendar.DATE, -(7 + (n - 1)));// 上周日的日期
        calendar.add(Calendar.DAY_OF_WEEK, 6);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    }
}
