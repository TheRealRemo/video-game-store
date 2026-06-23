package org.yearup.service;

import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.List;

@Service
public class ShoppingCartService {
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId) {
// Create an empty cart
        ShoppingCart shoppingCart = new ShoppingCart();


// Load all cart rows for the user
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

// Build the response by looking up each product
        for (CartItem cartItem : cartItems) {
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem item = new ShoppingCartItem();
            item.setProduct(product);
            item.setQuantity(cartItem.getQuantity());

            shoppingCart.add(item);
        }


        return shoppingCart;
    }

    public ShoppingCart addProduct(int userId, int productId) {

        CartItem existingItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);
// Check whether this product already exists in the user's cart
        if (existingItem == null) {
            // Product is not in the cart yet, create a new row
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(productId);
            cartItem.setQuantity(1);

            shoppingCartRepository.save(cartItem);
        } else {
            // Product already exists, increase the quantity
            existingItem.setQuantity(existingItem.getQuantity() + 1);

            shoppingCartRepository.save(existingItem);
        }

        // Return the updated cart
        return getByUserId(userId);
    }
    // add additional methods here
}
