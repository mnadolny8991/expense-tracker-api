package michaln.expensetracker.mapper;

import michaln.expensetracker.model.Expense;
import michaln.expensetracker.model.dto.ExpenseInputDto;
import org.springframework.stereotype.Service;

@Service
public class ExpenseDtoMapper {

    public Expense fromDto(ExpenseInputDto expenseDto) {
        return Expense.builder()
                .title(expenseDto.getTitle())
                .amount(expenseDto.getAmount())
                .unitPrice(expenseDto.getUnitPrice())
                .build();
    }
}
