package michaln.expensetracker.service;

import michaln.expensetracker.mapper.CategoryDtoMapper;
import michaln.expensetracker.model.Category;
import michaln.expensetracker.model.dto.CategoryInputDto;
import michaln.expensetracker.repository.CategoryRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public HttpEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }


    public HttpEntity<?> addCategory(CategoryInputDto categoryDto) {
        if (categoryRepository.existsCategoryByName(categoryDto.getName())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Category: category with such name already exists");
        }
        Category category = categoryDtoMapper.fromDto(categoryDto);
        categoryRepository.save(category);
        return ResponseEntity
                .created(URI.create("/categories/" + category.getId()))
                .build();
    }

    public HttpEntity<Category> getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public HttpEntity<?> updateCategory(Long id, CategoryInputDto categoryDto) {
        var categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        if (categoryRepository.existsCategoryByName(categoryDto.getName())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Category: category with such name already exists");
        }
        Category category = categoryOpt.get();
        categoryDtoMapper.updateFromDto(category, categoryDto);
        return ResponseEntity.ok().build();
    }

    public HttpEntity<?> deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
