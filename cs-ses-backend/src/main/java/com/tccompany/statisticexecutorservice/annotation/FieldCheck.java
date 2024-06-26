package com.tccompany.statisticexecutorservice.annotation;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class FieldCheck {

    public static boolean anyMatch(Object o1, Object o2) {
        boolean hasAnyMatch = false;
        List<Field> annotatedfieldsList;

        if (o1.getClass().isAnnotationPresent(SubscribeClass.class)) {
            annotatedfieldsList = Arrays.stream(o1.getClass().getDeclaredFields())
                    .filter(field ->
                            !field.isAnnotationPresent(SubscribeParam.class)
                                    || !field.getAnnotation(SubscribeParam.class).ignore()
                    ).toList();
        } else {
            annotatedfieldsList = Arrays.stream(o1.getClass().getDeclaredFields())
                    .filter(field ->
                            field.isAnnotationPresent(SubscribeParam.class) &&
                                    !field.getAnnotation(SubscribeParam.class).ignore())
                    .toList();
        }
        for (Field field : annotatedfieldsList) {
            try {
                field.setAccessible(true);
                if (field.get(o1) != null && field.get(o2) != null) {
                    if (field.get(o1).equals(field.get(o2))) {
                        hasAnyMatch = true;
                        return hasAnyMatch;
                    }
                }
            } catch (IllegalAccessException e) {
                log.throwing(e);
            }
        }
        return hasAnyMatch;
    }

}
