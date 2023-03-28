package br.com.cursoudemy.productapi.modules.product.controller;

import br.com.cursoudemy.productapi.config.SuccessResponse;
import br.com.cursoudemy.productapi.modules.product.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Integer id) {
        return service.findByIdResponse(id);
    }

    @GetMapping("name/{name}")
    public List<ProductResponse> findByDescription(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return service.findByCategoryId(categoryId);
    }

    @GetMapping("supplier/{supplierId}")
    public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId) {
        return service.findBySupplierId(supplierId);
    }

    @PutMapping("{id}")
    public ProductResponse update(@RequestBody ProductRequest request,
                                   @PathVariable Integer id) {
        return service.update(request, id);
    }

    @DeleteMapping("{id}")
    public SuccessResponse deleteById(@PathVariable Integer id) {
        return service.delete(id);
    }
}
