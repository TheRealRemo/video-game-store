package org.yearup.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.yearup.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;


    @InjectMocks
    private ProductService productService;

    @Test
    void search_NoFilters_ReturnsAllProducts() {

        // Arrange
        // Create two products that represent data that would normally come from the database.
        Product product1 = new Product(1, null, 1, 1, null, null, 5, false, null);
        Product product2 = new Product(2, null, 1, 2, null, null, 5, false, null);

        // Mock the repository so that calling findAll() returns our test products.
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        // Act
        // Call search with no filters to retrieve all products.
        List<Product> found = productService.search(null, null, null, null);

        // Assert
        // Verify that both products are returned.
        assertEquals(2, found.size());
    }

    @Test
    void update_ProductStockChanged_UpdatesNewStockValue() {
        // Arrange

        // Existing product in the "database"
        Product existingProduct = new Product(1, null, 1, 1, null, null, 5, false, null);

        // Product containing the updated information
        Product updatedProduct = new Product(1, null, 1, 1, null, null, 10, false, null);

        // Mock repository lookup
        when(productRepository.findById(1)).thenReturn(Optional.of(existingProduct));

        // Mock save operation
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product result = productService.update(1, updatedProduct);

        // Assert

        // Verify that the stock was updated from 5 to 10
        assertEquals(10, result.getStock());
    }
}