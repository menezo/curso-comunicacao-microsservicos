package br.com.cursoudemy.productapi.modules.supplier.service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository repository;

    public Supplier findById(Integer id) {
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
}
