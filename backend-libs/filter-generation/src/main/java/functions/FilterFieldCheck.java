package functions;

import annotations.FilterFieldClass;
import annotations.FilterFieldParam;
import dtos.FilterFieldsDto;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class FilterFieldCheck {

    /**
     * Returns a map of fields and their types for a given class.
     * @param o1 The class to be checked.
     * @return A map of fields and their types.
     */
    @Deprecated(since = "1.0")
    public static Map<String, String> mapOfObjectFilters(@NotNull(exception = NullPointerException.class) Class<?> o1) {
        List<Field> annotatedFieldsList = Arrays.stream(o1.getDeclaredFields())
            .filter(field -> !field.getName().equals("id") || !o1.isAnnotationPresent(FilterFieldClass.class) || !o1.getAnnotation(FilterFieldClass.class).idIgnore())
            .filter(field -> !field.isAnnotationPresent(FilterFieldParam.class) || !field.getAnnotation(FilterFieldParam.class).ignore())
            .toList();

        Map<String, String> fieldsMap = new HashMap<>();
        for (Field field : annotatedFieldsList) {
            if (isSimpleTypeOrEnum(field.getType()) || isWrapperType(field.getType())) {
                fieldsMap.put(field.getName(), convertPrimitiveToWrapper(field.getType()).getSimpleName());
            } else {
                Map<String, String> stringStringMap = mapOfObjectFilters(field.getType());
                stringStringMap.forEach((key, value) -> fieldsMap.put(field.getName() + "." + key, value));
            }
        }
        return fieldsMap;
    }

    public static List<FilterFieldsDto> listOfObjectFilters(Class<?> clazz) {
        boolean idIgnore = clazz.isAnnotationPresent(FilterFieldClass.class) && clazz.getAnnotation(FilterFieldClass.class).idIgnore();

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !field.isAnnotationPresent(FilterFieldParam.class) || !field.getAnnotation(FilterFieldParam.class).ignore())
                .filter(field -> !idIgnore || !field.getName().equals("id"))
                .flatMap(field -> processField(field).stream())
                .collect(Collectors.toList());
    }

    private static List<FilterFieldsDto> processField(Field field) {
        if (isSimpleTypeOrEnum(field.getType()) || isWrapperType(field.getType())) {
            return List.of(new FilterFieldsDto(field.getName(), convertPrimitiveToWrapper(field.getType()).getSimpleName()));
        } else {
            return mapOfObjectFilters(field.getType()).entrySet().stream()
                    .map(entry -> new FilterFieldsDto(field.getName() + "." + entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }
    }

    private static boolean isSimpleTypeOrEnum(Class<?> clazz) {
        return clazz.isPrimitive() || clazz.isEnum() || clazz.equals(String.class);
    }

    private static boolean isWrapperType(Class<?> clazz) {
        return Arrays.asList(Boolean.class, Integer.class, Character.class, Byte.class, Short.class, Double.class, Long.class, Float.class,
                Date.class, java.sql.Date.class, java.sql.Timestamp.class, java.sql.Time.class, java.time.LocalDate.class,
                java.time.LocalDateTime.class, java.time.LocalTime.class).contains(clazz);
    }

    private static Class<?> convertPrimitiveToWrapper(Class<?> clazz) {
        // Return the class if it's not primitive
        if (!clazz.isPrimitive()) return clazz;
        if (clazz.equals(int.class)) return Integer.class;
        if (clazz.equals(double.class)) return Double.class;
        if (clazz.equals(float.class)) return Float.class;
        if (clazz.equals(boolean.class)) return Boolean.class;
        if (clazz.equals(char.class)) return Character.class;
        if (clazz.equals(byte.class)) return Byte.class;
        if (clazz.equals(short.class)) return Short.class;
        if (clazz.equals(long.class)) return Long.class;
        // Add other primitives if necessary
        return clazz;
    }
}

