package pe.edu.vallegrande.hackathon.service;

import lombok.extern.slf4j.Slf4j;
import pe.edu.vallegrande.hackathon.model.clientModel;
import pe.edu.vallegrande.hackathon.repository.clientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class clientService {

    private final clientRepository clientRepository;

    public clientService(clientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<clientModel> findAll() {
        log.info("Listando todos los clientes");
        return clientRepository.findAll();
    }

    public Optional<clientModel> findById(Long id) {
        log.info("Buscando cliente con ID: {}", id);
        return clientRepository.findById(id);
    }

    public clientModel save(clientModel customer) {
        log.info("Registrando cliente: {}", customer);
        customer.setState(true);
        customer.setRegistration_date(LocalDateTime.now());
        return clientRepository.save(customer);
    }

    public clientModel update(clientModel customer) {
        log.info("Actualizando cliente: {}", customer);
        customer.setState(true);
        return clientRepository.save(customer);
    }

    public List<clientModel> findAllByState(Boolean state) {
        log.info("Listando clientes por estado: {}", state);
        return clientRepository.findAllByState(state);
    }

    public clientModel delete(Long id) {
        log.info("Eliminando lógicamente cliente con ID: {}", id);
        clientModel client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        client.setState(false);
        return clientRepository.save(client);
    }

    public clientModel restore(Long id) {
        log.info("Restaurando cliente con ID: {}", id);
        clientModel client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        client.setState(true);
        return clientRepository.save(client);
    }
}