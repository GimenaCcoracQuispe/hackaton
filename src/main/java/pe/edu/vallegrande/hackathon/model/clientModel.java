package pe.edu.vallegrande.hackathon.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Data
@Table(name = "customer")
public class clientModel {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "registration_date")
    private LocalDateTime registration_date;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "identification_document")
    private String identification_document;

    @Column(name = "document_number")
    private String document_number;

    @Column(name = "state")
    private Boolean state;

}
