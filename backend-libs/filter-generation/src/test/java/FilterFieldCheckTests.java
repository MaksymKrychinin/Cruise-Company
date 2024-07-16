import annotations.FilterFieldParam;
import dtos.FilterFieldsDto;
import functions.FilterFieldCheck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilterFieldCheckTests {
    @Test
    @DisplayName("listOfObjectFilters returns non-empty list for class with annotated fields")
    void listOfObjectFilters_returnsNonEmptyListForClassWithAnnotatedFields() {
        assertNotNull(FilterFieldCheck.mapOfObjectFilters(SampleClass.class));
        assertFalse(FilterFieldCheck.mapOfObjectFilters(SampleClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters returns empty list for class without annotated fields")
    void listOfObjectFilters_returnsEmptyListForClassWithoutAnnotatedFields() {
        assertTrue(FilterFieldCheck.mapOfObjectFilters(EmptySampleClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters ignores fields annotated with ignore=true")
    void listOfObjectFilters_ignoresFieldsAnnotatedWithIgnoreTrue() {
        assertTrue(FilterFieldCheck.mapOfObjectFilters(IgnoredFieldsClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters includes fields annotated with ignore=false")
    void listOfObjectFilters_includesFieldsAnnotatedWithIgnoreFalse() {
        assertFalse(FilterFieldCheck.mapOfObjectFilters(IncludedFieldsClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters handles null input gracefully")
    void listOfObjectFilters_handlesNullInputGracefully() {
        assertThrows(NullPointerException.class, () -> FilterFieldCheck.mapOfObjectFilters(null));
    }

    public static class SampleClass {
        @FilterFieldParam(ignore = false)
        private String includedField;
    }

    public static class EmptySampleClass {
        // No fields annotated with @FilterFieldParam
    }

    public static class IgnoredFieldsClass {
        @FilterFieldParam(ignore = true)
        private String ignoredField;
    }
    public static class IncludedFieldsClass {
        @FilterFieldParam(ignore = false)
        private String includedField;
    }


    @Test
    @DisplayName("mapOfObjectFilters returns correct field mappings for simple fields")
    void mapOfObjectFiltersReturnsCorrectFieldMappingsForSimpleFields() {
        assertEquals(Map.of("includedField", "String"), FilterFieldCheck.mapOfObjectFilters(SampleClass.class));
    }

    @Test
    @DisplayName("mapOfObjectFilters returns correct field mappings for nested objects")
    void mapOfObjectFiltersReturnsCorrectFieldMappingsForNestedObjects() {
        assertEquals(Map.of("nestedObject.includedField", "String"), FilterFieldCheck.mapOfObjectFilters(NestedObjectClass.class));
    }

    @Test
    @DisplayName("mapOfObjectFilters returns empty map for class without fields")
    void mapOfObjectFiltersReturnsEmptyMapForClassWithoutFields() {
        assertTrue(FilterFieldCheck.mapOfObjectFilters(EmptySampleClass.class).isEmpty());
    }

    @Test
    @DisplayName("mapOfObjectFilters excludes fields marked with ignore")
    void mapOfObjectFiltersExcludesFieldsMarkedWithIgnore() {
        assertTrue(FilterFieldCheck.mapOfObjectFilters(IgnoredFieldsClass.class).isEmpty());
    }

    @Test
    @DisplayName("mapOfObjectFilters handles null input by throwing NullPointerException")
    void mapOfObjectFiltersHandlesNullInputByThrowingNullPointerException() {
        assertThrows(NullPointerException.class, () -> FilterFieldCheck.mapOfObjectFilters(null));
    }

    @Test
    @DisplayName("mapOfObjectFilters includes enum fields correctly")
    void mapOfObjectFiltersIncludesEnumFieldsCorrectly() {
        assertEquals(Map.of("status", "StatusEnum"), FilterFieldCheck.mapOfObjectFilters(EnumFieldClass.class));
    }

    @Test
    @DisplayName("mapOfObjectFilters correctly processes classes with wrapper and primitive types")
    void mapOfObjectFiltersCorrectlyProcessesClassesWithWrapperAndPrimitiveTypes() {
        Map<String, String> expected = Map.of(
                "booleanField", "Boolean",
                "intField", "Integer",
                "doubleField", "Double",
                "stringField", "String"
        );
        assertEquals(expected, FilterFieldCheck.mapOfObjectFilters(WrapperAndPrimitiveTypesClass.class));
    }

    public class WrapperAndPrimitiveTypesClass {
        private Boolean booleanField;
        private int intField;
        private Double doubleField;
        private String stringField;

        // Getters and setters for each field
        public Boolean getBooleanField() {
            return booleanField;
        }

        public void setBooleanField(Boolean booleanField) {
            this.booleanField = booleanField;
        }

        public int getIntField() {
            return intField;
        }

        public void setIntField(int intField) {
            this.intField = intField;
        }

        public Double getDoubleField() {
            return doubleField;
        }

        public void setDoubleField(Double doubleField) {
            this.doubleField = doubleField;
        }

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }
    }

    public static class NestedObjectClass {
        public static class NestedObject {
            private String includedField;

            public String getIncludedField() {
                return includedField;
            }

            public void setIncludedField(String includedField) {
                this.includedField = includedField;
            }
        }

        private NestedObject nestedObject = new NestedObject();

        public NestedObject getNestedObject() {
            return nestedObject;
        }

        public void setNestedObject(NestedObject nestedObject) {
            this.nestedObject = nestedObject;
        }
    }

    public static class EnumFieldClass {
        public enum StatusEnum {
            ACTIVE, INACTIVE
        }

        private StatusEnum status;

        public StatusEnum getStatus() {
            return status;
        }

        public void setStatus(StatusEnum status) {
            this.status = status;
        }
    }

    @Test
    @DisplayName("listOfObjectFilters returns list with correct mappings for simple and primitive fields")
    void listOfObjectFilters_ReturnsListWithCorrectMappingsForSimpleAndPrimitiveFields() {
        List<FilterFieldsDto> expected = List.of(
                new FilterFieldsDto("booleanField", "Boolean"),
                new FilterFieldsDto("intField", "Integer"),
                new FilterFieldsDto("doubleField", "Double"),
                new FilterFieldsDto("stringField", "String")
        );
        assertEquals(expected, FilterFieldCheck.listOfObjectFilters(WrapperAndPrimitiveTypesClass.class));
    }

    @Test
    @DisplayName("listOfObjectFilters returns list with correct mappings for nested objects")
    void listOfObjectFilters_ReturnsListWithCorrectMappingsForNestedObjects() {
        List<FilterFieldsDto> expected = List.of(
                new FilterFieldsDto("nestedObject.includedField", "String")
        );
        assertEquals(expected, FilterFieldCheck.listOfObjectFilters(NestedObjectClass.class));
    }

    @Test
    @DisplayName("listOfObjectFilters returns empty list for class without annotated fields")
    void listOfObjectFilters_ReturnsEmptyListForClassWithoutAnnotatedFields() {
        assertTrue(FilterFieldCheck.listOfObjectFilters(EmptySampleClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters ignores fields annotated with ignore=true")
    void listOfObjectFilters_IgnoresFieldsAnnotatedWithIgnoreTrue() {
        assertTrue(FilterFieldCheck.listOfObjectFilters(IgnoredFieldsClass.class).isEmpty());
    }

    @Test
    @DisplayName("listOfObjectFilters includes fields annotated with ignore=false")
    void listOfObjectFilters_IncludesFieldsAnnotatedWithIgnoreFalse() {
        List<FilterFieldsDto> expected = List.of(
                new FilterFieldsDto("includedField", "String")
        );
        assertEquals(expected, FilterFieldCheck.listOfObjectFilters(IncludedFieldsClass.class));
    }

    @Test
    @DisplayName("listOfObjectFilters handles null input by throwing NullPointerException")
    void listOfObjectFilters_HandlesNullInputByThrowingNullPointerException() {
        assertThrows(NullPointerException.class, () -> FilterFieldCheck.listOfObjectFilters(null));
    }

    @Test
    @DisplayName("listOfObjectFilters includes enum fields correctly")
    void listOfObjectFilters_IncludesEnumFieldsCorrectly() {
        List<FilterFieldsDto> expected = List.of(
                new FilterFieldsDto("status", "StatusEnum")
        );
        assertEquals(expected, FilterFieldCheck.listOfObjectFilters(EnumFieldClass.class));
    }
}