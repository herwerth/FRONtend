package hu.progmasters.backend.controller;

import hu.progmasters.backend.dto.CategoryInfo;
import hu.progmasters.backend.dto.RegisterCategoryCommand;
import hu.progmasters.backend.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Slf4j
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryInfo> saveCategory(@Valid @RequestBody RegisterCategoryCommand command) {
        log.info("Http request POST / /api/categories with data: " + command.toString());
        CategoryInfo categoryInfo = categoryService.saveCategory(command);
        return new ResponseEntity<>(categoryInfo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryInfo>> listCategories() {
        log.info("Http request GET / /api/categories");
        List<CategoryInfo> categoryInfoList = categoryService.listCategories();
        return new ResponseEntity<>(categoryInfoList, HttpStatus.OK);
    }
}
