package pe.edu.vallegrande.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.vallegrande.hackathon.model.clientModel;

import java.util.List;

public interface clientRepository extends JpaRepository<clientModel, Long> {
List<clientModel> findAllByState(Boolean state);
}