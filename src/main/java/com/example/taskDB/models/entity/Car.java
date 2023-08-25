package com.example.taskDB.models.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String vin_number;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    @NotNull
    private Model model;

    @Column(nullable = false)
    @NotNull
    private BigDecimal price;

    @Column(nullable = false)
    @NotNull
    private Date reg_date;

    @ManyToOne
    @JoinColumn(name = "transmission_id", nullable = false)
    @NotNull
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id", nullable = false)
    @NotNull
    private FuelType fuelType;

    @Column(nullable = false)
    @NotNull
    private String remarks;
}