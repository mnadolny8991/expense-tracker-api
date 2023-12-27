package michaln.expensetracker.controller;

import jakarta.validation.Valid;
import michaln.expensetracker.model.Category;
import michaln.expensetracker.model.dto.CategoryInputDto;
import michaln.expensetracker.service.CategoryService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public HttpEntity<List<Category>> getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public HttpEntity<Category> getCategory(@PathVariable(name = "id") Long id) {
        return categoryService.getCategory(id);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> updateCategory(@PathVariable(name = "id") Long id,
                                        @RequestBody @Valid CategoryInputDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @PostMapping
    public HttpEntity<?> addCategory(@RequestBody @Valid CategoryInputDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable(name = "id") Long id) {
        return categoryService.deleteCategory(id);
    }
}
