package com.example.taskDB.models.entity;

import com.example.taskDB.models.enums.ModelName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@Entity
@Table(name = "MODEL")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private ModelName name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    @NotNull
    private Brand brand;


}