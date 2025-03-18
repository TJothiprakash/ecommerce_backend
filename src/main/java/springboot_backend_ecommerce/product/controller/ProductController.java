package springboot_backend_ecommerce.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot_backend_ecommerce.product.entity.Product;
import springboot_backend_ecommerce.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {

        logger.info("Fetching all products");
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Fetching product with ID: {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }

    // Search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        logger.info("Searching for products with name: {}", name);
        return ResponseEntity.ok(productService.searchProducts(name));
    }

    // Get products by category
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        logger.info("Fetching products in category: {}", category);
        return ResponseEntity.ok(productService.getProductsByCategory(category));
    }

    // Add new product (Admin only)
    @PostMapping("/addproduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        logger.info("Adding new product: {}", product);
        return ResponseEntity.ok(productService.addProduct(product));
    }

    // Update product (Admin only)
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        logger.info("Updating product with ID: {}, New Data: {}", id, product);
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    // Delete product (Admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
