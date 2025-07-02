package pe.edu.vallegrande.hackathon.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.vallegrande.hackathon.model.clientModel;
import pe.edu.vallegrande.hackathon.service.clientService;

import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class clientRest {

    private final clientService ClientService;

    public clientRest(clientService ClientService) {
        this.ClientService = ClientService;
    }

    @GetMapping
    public List <clientModel> findAll(){
        return ClientService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<clientModel> findById(@PathVariable Long id) {
        return ClientService.findById(id);
    }

    @PostMapping("/save")
    public clientModel save(@RequestBody clientModel customer) {
        return ClientService.save(customer);
    }

    @PutMapping("/update")
    public clientModel update(@RequestBody clientModel customer) {
        return ClientService.update(customer);
    }
    @GetMapping("/state/{state}")
    public List<clientModel> findByState(@PathVariable Boolean state) {
        return ClientService.findAllByState(state);
    }
    @PutMapping("/delete/{id}")
    public clientModel delete(@PathVariable Long id) {
        return ClientService.delete(id);
    }
    @PutMapping("/restore/{id}")
    public clientModel restore(@PathVariable Long id) {
        return ClientService.restore(id);
    }
}
