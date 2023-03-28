package br.com.cursoudemy.productapi.modules.supplier.service;

import br.com.cursoudemy.productapi.config.SuccessResponse;
import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;
    @Autowired
    private ProductService productService;

    public List<SupplierResponse> findAll() {
        return repository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public List<SupplierResponse> findByName(String name) {
        if (isEmpty(name)) {
            throw new ValidationException("Supplier name must be informed.");
        }
        return repository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of(findById(id));
    }

    public Supplier findById(Integer id) {
        validateId(id);
        return repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("There is no Supplier for the given ID"));
    }

    public SupplierResponse save(SupplierRequest request) {
        validateSupplierName(request);
        var supplier = repository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);
    }

    private void validateSupplierName(SupplierRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The supplier name was not informed");
        }
    }

    public SuccessResponse delete(Integer id) {
        validateId(id);
        if (productService.existsBySupplierId(id)) {
            throw new ValidationException("You cannot delete this supplier because it is already defined by a product.");
        }
        repository.deleteById(id);
        return SuccessResponse.create("The supplier was deleted.");
    }

    public void validateId(Integer id) {
        if (isEmpty(id)) {
            throw new ValidationException("Supplier ID must be informed");
        }
    }
}
