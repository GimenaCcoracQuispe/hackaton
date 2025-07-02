package pe.edu.vallegrande.hackathon.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.vallegrande.hackathon.service.productService;
import pe.edu.vallegrande.hackathon.model.productModel;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/products")
public class productRest {
    private final productService productService;

    public productRest(productService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<productModel> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<productModel> getProductById(@PathVariable Long id) {
        Optional<productModel> product = productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public List<productModel> getActiveProducts() {
        return productService.findAllByStatus("A");
    }

    @GetMapping("/inactivos")
    public List<productModel> getInactiveProducts() {
        return productService.findAllByStatus("I");
    }

    @PostMapping("/create")
    public ResponseEntity<productModel> createProduct(@RequestBody productModel product) {
        productModel savedProduct = productService.save(product);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<productModel> updateProduct(@PathVariable Long id, @RequestBody productModel product) {
        product.setProduct_id(id);
        productModel updatedProduct = productService.update(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteLogic(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePhysicalProduct(@PathVariable Long id) {
        productService.deletePhysical(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreProduct(@PathVariable Long id) {
        productService.restoreLogic(id);
        return ResponseEntity.noContent().build();
    }
}