package dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterFieldsDto {
    private String fieldName;
    private String fieldType;
    /**
     * Still in Development.
     * If fieldType is an enum, enumValues will contain the list of possible values
     */
    private List<String> enumValues;

    public FilterFieldsDto(String fieldName, String fieldType) {
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }
}
