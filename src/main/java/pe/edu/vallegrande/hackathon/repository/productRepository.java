package pe.edu.vallegrande.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.vallegrande.hackathon.model.productModel;

import java.util.List;

public interface productRepository extends JpaRepository<productModel, Long> {
    List<productModel> findAllByStatus(String status);
}
