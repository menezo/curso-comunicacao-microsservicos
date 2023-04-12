package br.com.cursoudemy.productapi.modules.supplier.controller;

import br.com.cursoudemy.productapi.config.SuccessResponse;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/supplier")
public class SupplierController {

    private final SupplierService service;

    @PostMapping
    public SupplierResponse save(@RequestBody SupplierRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<SupplierResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public SupplierResponse findById(@PathVariable Integer id) {
        return service.findByIdResponse(id);
    }

    @GetMapping("name/{name}")
    public List<SupplierResponse> findByDescription(@PathVariable String name) {
        return service.findByName(name);
    }

    @PutMapping("{id}")
    public SupplierResponse update(@RequestBody SupplierRequest request,
                                   @PathVariable Integer id) {
        return service.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse deleteById(@PathVariable Integer id) {
        return service.delete(id);
    }
}
