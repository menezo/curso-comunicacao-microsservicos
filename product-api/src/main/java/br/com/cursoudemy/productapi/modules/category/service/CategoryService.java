package br.com.cursoudemy.productapi.modules.category.service;

import br.com.cursoudemy.productapi.config.SuccessResponse;
import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@AllArgsConstructor(onConstructor_ = { @Lazy} )
public class CategoryService {

    private final CategoryRepository repository;

    @Lazy
    private final ProductService productService;

    public CategoryResponse findByIdResponse(Integer id) {
        return CategoryResponse.of(findById(id));
    }
    public List<CategoryResponse> findAll() {
        return repository
                .findAll()
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public List<CategoryResponse> findByDescription(String description) {
        if (isEmpty(description)) {
            throw new ValidationException("Category description must be informed.");
        }
        return repository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    public Category findById(Integer id) {
        validateId(id);
        return repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There is no Category for the given ID"));
    }

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryName(request);
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    public CategoryResponse update(CategoryRequest request,
                                 Integer id) {
        validateCategoryName(request);
        validateId(id);
        findById(id);
        var category = Category.of(request);
        category.setId(id);
        repository.save(category);
        return CategoryResponse.of(category);
    }

    private void validateCategoryName(CategoryRequest request) {
        if (isEmpty(request.getDescription())) {
            throw new ValidationException("The category description was not informed.");
        }
    }

    public SuccessResponse delete(Integer id) {
        validateId(id);
        if (productService.existsByCategoryId(id)) {
            throw new ValidationException("You cannot delete this category because it is already defined by a product.");
        }
        repository.deleteById(id);
        return SuccessResponse.create("The category was deleted.");
    }

    public void validateId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("Category ID must be informed");
        }
    }
}