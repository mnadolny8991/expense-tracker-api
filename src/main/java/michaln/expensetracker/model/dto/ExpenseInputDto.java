package michaln.expensetracker.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpenseInputDto {
    @NotNull(message = "Expense: title cannot be null")
    @NotBlank(message = "Expense: title cannot be blank")
    private String title;
    @NotNull(message = "Expense: unitPrice cannot be null")
    private double unitPrice;
    @NotNull(message = "Expense: amount cannot be null")
    private int amount;
}
