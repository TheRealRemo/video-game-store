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

import static org.junit.jupiter.api.Assertions.*;
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

        // Verify that the returned list contains the expected products
        // and that they are returned in the same order.
        assertEquals(product1, found.get(0));
        assertEquals(product2, found.get(1));
    }

}