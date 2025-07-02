package main.java.pe.edu.vallegrande.hackathon.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class productModel {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    private String name;
    private String description;
    private String category;
    private String brand;

    @Column(name = "unit_measurement")
    private String unit_measurement;

    @Column(name = "unit_price")
    private BigDecimal unit_price;

    private Integer stock;

    @Column(name = "minimum_stock")
    private Integer minimum_stock;

    @Column(name = "expiration_date")
    private LocalDate expiration_date;

    @Column(name = "high_date", insertable = false, updatable = false)
    private Timestamp high_date;

    private String status;
}
