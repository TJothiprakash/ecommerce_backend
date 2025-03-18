package springboot_backend_ecommerce.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot_backend_ecommerce.cart.entity.Cart;
import springboot_backend_ecommerce.cart.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Get cart for user
    @GetMapping("/{userId}")
    public ResponseEntity<springboot_backend_ecommerce.cart.entity.Cart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCartByUserId(userId));
    }

    // Add product to cart
    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<springboot_backend_ecommerce.cart.entity.Cart> addToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartService.addToCart(userId, productId, quantity));
    }

    // Remove product from cart
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeFromCart(userId, productId));
    }

    // Clear cart
    @DeleteMapping("/{userId}/clear")
    public ResponseEntity<Cart> clearCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.clearCart(userId));
    }
}
