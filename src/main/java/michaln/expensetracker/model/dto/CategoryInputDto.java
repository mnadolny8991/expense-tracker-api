package michaln.expensetracker.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInputDto {
    @NotBlank(message = "Category: name cannot be blank")
    @NotNull(message = "Category: name cannot be null")
    private String name;
    private List<CategoryInputDto> categories;
}
