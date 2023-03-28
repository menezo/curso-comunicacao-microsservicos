package br.com.cursoudemy.productapi.modules.category.service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category findById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There is no Category for the given ID"));
    }

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryName(request);
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    private void validateCategoryName(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }
}