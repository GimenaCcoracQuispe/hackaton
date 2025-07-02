package pe.edu.vallegrande.hackathon.service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.vallegrande.hackathon.model.productModel;
import pe.edu.vallegrande.hackathon.repository.productRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service

public class productService {
    private final productRepository productRepository;

    public productService(productRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<productModel> findAll() {
        log.info("Listando Todos los Productos");
        return productRepository.findAll();
    }

    public Optional<productModel> findById(Long id) {
        log.info("Buscando Producto por ID: {}", id);
        return productRepository.findById(id);
    }

    public productModel save(productModel product) {
        log.info("Registrando Producto: {}", product.toString());
        product.setStatus("A");
        return productRepository.save(product);
    }

    public productModel update(productModel product) {
        log.info("Actualizando Producto: {}", product.toString());
        return productRepository.findById(product.getProduct_id())
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setBrand(product.getBrand());
                    existingProduct.setUnit_measurement(product.getUnit_measurement());
                    existingProduct.setUnit_price(product.getUnit_price());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setMinimum_stock(product.getMinimum_stock());
                    existingProduct.setExpiration_date(product.getExpiration_date());
                    existingProduct.setHigh_date(product.getHigh_date());
                    existingProduct.setStatus(product.getStatus() != null ? product.getStatus() : "A");
                    return productRepository.save(existingProduct);
                })
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void deleteLogic(Long id) {
        log.info("Eliminando Lógicamente Producto con ID: {}", id);
        Optional<productModel> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            productModel product = productOpt.get();
            product.setStatus("I");
            productRepository.save(product);
        }
    }

    public void restoreLogic(Long id) {
        log.info("Restaurando Producto con ID: {}", id);
        Optional<productModel> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            productModel product = productOpt.get();
            product.setStatus("A");
            productRepository.save(product);
        }
    }

    public List<productModel> findAllByStatus(String status) {
        log.info("Listando Productos con estado: {}", status);
        return productRepository.findAllByStatus(status);
    }

    public void deletePhysical(Long id) {
        log.info("Eliminando físicamente el Producto con ID: {}", id);
        productRepository.deleteById(id);
    }
}
