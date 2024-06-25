package functions;

import annotations.FilterFieldClass;
import annotations.FilterFieldParam;
import dtos.FilterFieldsDto;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Field;
import java.util.*;

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
                        )
                        .filter(field -> !field.getName().equals("id"))
                        .toList();
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

    public static List<FilterFieldsDto> listOfObjectFilters(Class<?> o1) {
        List<Field> annotatedfieldsList;
        if (o1.isAnnotationPresent(FilterFieldClass.class)) {
            if (o1.getAnnotation(FilterFieldClass.class).idIgnore()) {
                annotatedfieldsList = Arrays.stream(o1.getDeclaredFields())
                        .filter(field ->
                                !field.isAnnotationPresent(FilterFieldParam.class)
                                        || !field.getAnnotation(FilterFieldParam.class).ignore()
                        )
                        .filter(field -> !field.getName().equals("id"))
                        .toList();
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

        List<FilterFieldsDto> fieldsDtoList = new ArrayList<>();
        for (Field field : annotatedfieldsList) {
            if (field.getType().isPrimitive() || field.getType().isEnum() || field.getType().isAssignableFrom(String.class)
            || isWrapperType(field.getType())) {
                fieldsDtoList.add(new FilterFieldsDto(field.getName(), field.getType().getSimpleName()));
            } else {
                Map<String, String> stringStringMap = mapOfObjectFilters(field.getType());
                stringStringMap.forEach((key, value) -> fieldsDtoList.add(new FilterFieldsDto(field.getName() + "." + key, value)));
            }
        }
        return fieldsDtoList;
    }

    public static boolean isWrapperType(Class<?> clazz) {
        return clazz.equals(Boolean.class) ||
                clazz.equals(Integer.class) ||
                clazz.equals(Character.class) ||
                clazz.equals(Byte.class) ||
                clazz.equals(Short.class) ||
                clazz.equals(Double.class) ||
                clazz.equals(Long.class) ||
                clazz.equals(Float.class)||
                clazz.equals(Date.class)||
                clazz.equals(java.sql.Date.class)||
                clazz.equals(java.sql.Timestamp.class)||
                clazz.equals(java.sql.Time.class)||
                clazz.equals(java.time.LocalDate.class)||
                clazz.equals(java.time.LocalDateTime.class)||
                clazz.equals(java.time.LocalTime.class);
    }
}
