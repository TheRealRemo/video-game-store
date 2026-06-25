package org.yearup.service;

import jakarta.transaction.Transactional;
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

    /// The method retrieves the user's cart rows, looks up each associated product,
    /// combines the data into ShoppingCartItems,
    ///  and returns a ShoppingCart that the frontend can display.
    public ShoppingCart getByUserId(int userId) {
        // Create an empty ShoppingCart object that will be populated
        // with the user's products and quantities.
        ShoppingCart shoppingCart = new ShoppingCart();

        // Retrieve all cart rows belonging to the specified user.
        // Each CartItem only contains database information such as:
        // userId, productId, and quantity.
        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        // Convert each database CartItem into a ShoppingCartItem
        // that contains the full Product details needed by the frontend.
        for (CartItem cartItem : cartItems) {
            // Look up the complete Product using the productId stored
            // in the cart row. This provides the name, price, description,
            // image URL, and other product information.
            Product product = productService.getById(cartItem.getProductId());

            // Create a ShoppingCartItem that combines the Product
            // information with the quantity stored in the cart.
            ShoppingCartItem item = new ShoppingCartItem();

            // Attach the full Product object to the cart item.
            item.setProduct(product);

            // Copy the quantity from the database cart row.
            item.setQuantity(cartItem.getQuantity());

            // Add the completed ShoppingCartItem to the ShoppingCart.
            shoppingCart.add(item);
        }

        // Return the fully populated ShoppingCart object to the controller,
        // which will send it back to the frontend as the API response.
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

    public ShoppingCart updateCart(int userId, int productId, int quantity) {
        CartItem existingItem = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if (existingItem != null) {
            existingItem.setQuantity(quantity);

            shoppingCartRepository.save(existingItem);
        }

        return getByUserId(userId);
    }

    // This method is marked @Transactional because the custom
   // deleteByUserId() operation requires an active database
  // transaction in order to execute successfully.
    @Transactional
    public ShoppingCart clearCart(int userId) {

        // Delete all shopping cart items that belong to the specified user.
        shoppingCartRepository.deleteByUserId(userId);

        // Return the user's updated (now empty) shopping cart.
        return getByUserId(userId);
    }
}
