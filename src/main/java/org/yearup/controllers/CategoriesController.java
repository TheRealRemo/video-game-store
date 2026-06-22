package org.yearup.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.service.CategoryService;
import org.yearup.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController {
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public CategoriesController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        Category category = categoryService.getById(id);
        if (category == null) {
            // Return 404 when the requested category does not exist.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Category with id" + id);
        }
        return category;
    }


    @GetMapping("/{id}/products")
    public List<Product> getProductsById(@PathVariable int id) {
        // Verify that the category exists before retrieving its products.
        Category category = categoryService.getById(id);
        if (category == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Category with id " + id);
        }
        return productService.listByCategoryId(id);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        // Only ADMIN users can create categories.
        // Return HTTP 201 Created after successful insertion.
        Category created = categoryService.create(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(@PathVariable int id, @RequestBody Category category) {
        // Update the existing category and return 404 if it does not exist.
        Category saved = categoryService.update(id, category);
        if (saved == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found with id " + id);
        }
        return saved;
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        // Verify the category exists before deleting.
        if (categoryService.getById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No category found with id " + id);
        }
        categoryService.delete(id);
        // Return 204 because the resource was successfully deleted.
        return ResponseEntity.noContent().build();
    }
}
