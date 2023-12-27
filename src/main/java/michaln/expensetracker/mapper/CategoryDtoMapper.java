package michaln.expensetracker.mapper;

import michaln.expensetracker.model.Category;
import michaln.expensetracker.model.dto.CategoryInputDto;
import org.springframework.stereotype.Service;

@Service
public class CategoryDtoMapper {

    public Category fromDto(CategoryInputDto categoryDto) {
        return new Category(categoryDto.getName());
    }

    public void updateFromDto(Category toUpdate, CategoryInputDto categoryDto) {
        toUpdate.setName(categoryDto.getName());
    }
}
