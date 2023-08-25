package com.example.taskDB.repository;

import com.example.taskDB.models.entity.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {}