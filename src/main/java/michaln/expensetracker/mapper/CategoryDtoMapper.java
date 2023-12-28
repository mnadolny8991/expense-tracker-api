package michaln.expensetracker.mapper;

import michaln.expensetracker.model.Category;
import michaln.expensetracker.model.dto.CategoryInputDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDtoMapper {

    public Category fromDto(CategoryInputDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());

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
