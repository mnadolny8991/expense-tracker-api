package michaln.expensetracker.mapper;

import michaln.expensetracker.model.Category;
import michaln.expensetracker.model.Expense;
import michaln.expensetracker.model.dto.CategoryInputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDtoMapper {

    private final ExpenseDtoMapper expenseDtoMapper;

    public CategoryDtoMapper(ExpenseDtoMapper expenseDtoMapper) {
        this.expenseDtoMapper = expenseDtoMapper;
    }

    public Category fromDto(CategoryInputDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

        for (var expenseDto : categoryDto.getExpenses()) {
            Expense expense = expenseDtoMapper.fromDto(expenseDto);
            expense.setCategory(category);
            category.getExpenses().add(expense);
        }

        if (categoryDto.getCategories() == null ) {
            category.setChildCategories(null);
            return category;
        }

        for (var subcategoryDto : categoryDto.getCategories()) {
            Category subcategory = fromDto(subcategoryDto);
            subcategory.setParentCategory(category);
            category.getChildCategories().add(subcategory);
        }
        return category;
    }
}
