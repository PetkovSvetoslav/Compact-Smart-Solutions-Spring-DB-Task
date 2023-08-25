package com.example.taskDB.models.entity;

import com.example.taskDB.models.enums.TransmissionName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Entity
@Table(name = "TRANSMISSION")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private TransmissionName name;
}
