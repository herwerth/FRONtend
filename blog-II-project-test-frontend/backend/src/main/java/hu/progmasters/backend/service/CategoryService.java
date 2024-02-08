package hu.progmasters.backend.service;

import hu.progmasters.backend.domain.Category;
import hu.progmasters.backend.dto.CategoryInfo;
import hu.progmasters.backend.dto.RegisterCategoryCommand;
import hu.progmasters.backend.exceptionhandling.CategoryNotFoundByIdException;
import hu.progmasters.backend.exceptionhandling.CategoryWithNameAlreadyExistsException;
import hu.progmasters.backend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public CategoryInfo saveCategory(RegisterCategoryCommand command) {
        String categoryName = categoryRepository.findCategoryName(command.getName());
        if (categoryName != null && categoryName.equals(command.getName())) {
            throw new CategoryWithNameAlreadyExistsException(command.getName());
        }
        Category category = categoryRepository.save(modelMapper.map(command, Category.class));
        return modelMapper.map(category, CategoryInfo.class);
    }

    public List<CategoryInfo> listCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryInfo.class))
                .collect(Collectors.toList());
    }

    public Category findCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundByIdException(id);
        }
        return optionalCategory.get();
    }
}
