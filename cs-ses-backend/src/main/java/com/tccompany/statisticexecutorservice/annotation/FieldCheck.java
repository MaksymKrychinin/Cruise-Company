package com.tccompany.statisticexecutorservice.annotation;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class FieldCheck {

    public static boolean anyMatch(Object o1, Object o2) {
        List<Field> fieldsList = Arrays.stream(o1.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(SubscribeParam.class) &&
                        !field.getAnnotation(SubscribeParam.class).ignore())
                .toList();

        for (Field field : fieldsList) {
            try {
                field.setAccessible(true);
                Object value1 = field.get(o1);
                Object value2 = field.get(o2);
                if (value1 != null && value2 != null && value1.equals(value2)) {
                    return true;
                }
            } catch (IllegalAccessException e) {
                log.throwing(e);
            }
        }
        return false;
    }
}