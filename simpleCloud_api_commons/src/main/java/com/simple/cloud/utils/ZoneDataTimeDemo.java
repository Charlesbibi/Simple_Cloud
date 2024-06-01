package com.simple.cloud.utils;

import java.time.ZonedDateTime;

/**
 * @author Charles
 * @create 2024-04-16-16:34
 */
public class ZoneDataTimeDemo {
    public static void main(String[] args) {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        System.out.println(zbj);
    }
}