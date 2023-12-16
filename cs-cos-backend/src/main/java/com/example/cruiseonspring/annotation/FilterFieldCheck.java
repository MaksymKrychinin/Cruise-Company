package com.example.cruiseonspring.annotation;

import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class FilterFieldCheck {

    public static Map<String, String> mapOfObjectFilters(Class<?> o1) {
        List<Field> annotatedfieldsList;
        if (o1.isAnnotationPresent(FilterFieldClass.class)) {
            if (o1.getAnnotation(FilterFieldClass.class).idIgnore()) {
                annotatedfieldsList = Arrays.stream(o1.getDeclaredFields())
                        .filter(field ->
                                !field.isAnnotationPresent(FilterFieldParam.class)
                                        || !field.getAnnotation(FilterFieldParam.class).ignore()
                        ).toList();
            } else {
                annotatedfieldsList = Arrays.stream(o1.getDeclaredFields())
                        .filter(field ->
                                !field.isAnnotationPresent(FilterFieldParam.class)
                                        || !field.getAnnotation(FilterFieldParam.class).ignore()
                        ).toList();
            }

        } else {
            annotatedfieldsList = Arrays.stream(o1.getDeclaredFields())
                    .filter(field ->
                            field.isAnnotationPresent(FilterFieldParam.class) &&
                                    !field.getAnnotation(FilterFieldParam.class).ignore())
                    .toList();
        }

        Map<String, String> fieldsMap = new HashMap<>();
        for (Field field : annotatedfieldsList) {
            if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType().isAssignableFrom(String.class)) {
                fieldsMap.put(field.getName(), field.getType().getSimpleName());
            } else {
                Map<String, String> stringStringMap = mapOfObjectFilters(field.getType());
                stringStringMap.forEach((key, value) -> fieldsMap.put(field.getName() + "." + key, value));
            }
        }
        return fieldsMap;
    }

}
