package com.example.taskDB.models.entity;

import com.example.taskDB.models.enums.FuelTypeName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Entity
@Table(name = "FUEL_TYPE")
public class FuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private FuelTypeName name;
}
