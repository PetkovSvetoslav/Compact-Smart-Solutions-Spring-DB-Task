package com.example.taskDB.controllers;

import com.example.taskDB.models.entity.*;
import com.example.taskDB.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> createCar(@Valid @RequestBody Car car) {
        return ResponseEntity.ok(carService.createCar(car));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        return carService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @Valid @RequestBody Car updatedCar) {
        return ResponseEntity.ok(carService.updateCar(id, updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(
            @RequestParam Optional<BigDecimal> price,
            @RequestParam Optional<Long> modelId,
            @RequestParam Optional<Long> brandId,
            @RequestParam Optional<Long> fuelTypeId,
            @RequestParam Optional<Date> regDate,
            @RequestParam Optional<Long> transmissionId,
            @RequestParam Optional<String> remarks) {

        List<Car> results = carService.searchCars(price, modelId, brandId, fuelTypeId, regDate, transmissionId, remarks);
        return ResponseEntity.ok(results);
    }




    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}

