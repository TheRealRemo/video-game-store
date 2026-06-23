package org.yearup.controllers;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.yearup.service.ProductService;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(ProductsController.class)
class ProductsControllerTest  {
    @Autowired
    Mock mockMvc;

    @MockitoBean
    ProductService productService;


}