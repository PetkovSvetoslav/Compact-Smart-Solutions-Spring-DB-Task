package com.example.taskDB.service;

import com.example.taskDB.models.entity.*;
import com.example.taskDB.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final TransmissionRepository transmissionRepository;

    public CarService(CarRepository carRepository, ModelRepository modelRepository, BrandRepository brandRepository, FuelTypeRepository fuelTypeRepository, TransmissionRepository transmissionRepository) {
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.transmissionRepository = transmissionRepository;
    }


    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }
    public Car updateCar(Long id, Car car) {
        if (carRepository.existsById(id)) {
            return carRepository.save(car);
        } else {
            throw new EntityNotFoundException("Car with ID " + id + " not found.");
        }
    }

    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Car with ID " + id + " not found.");
        }
    }

    public List<Car> searchCars(Optional<BigDecimal> price,
                                Optional<Long> modelId,
                                Optional<Long> brandId,
                                Optional<Long> fuelTypeId,
                                Optional<Date> regDate,
                                Optional<Long> transmissionId,
                                Optional<String> remarks) {

        Model model = modelId.isPresent() ? modelRepository.findById(modelId.get()).orElse(null) : null;
        Brand brand = brandId.isPresent() ? brandRepository.findById(brandId.get()).orElse(null) : null;
        FuelType fuelType = fuelTypeId.isPresent() ? fuelTypeRepository.findById(fuelTypeId.get()).orElse(null) : null;
        Transmission transmission = transmissionId.isPresent() ? transmissionRepository.findById(transmissionId.get()).orElse(null) : null;

        // Validate the price if it's present
        price.ifPresent(p -> {
            if (p.signum() <= 0) {
                throw new IllegalArgumentException("Price must be positive");
            }
        });

        // Validate entities if they're present.
        Optional.ofNullable(model).ifPresent(this::validateModel);
        Optional.ofNullable(brand).ifPresent(this::validateBrand);
        Optional.ofNullable(fuelType).ifPresent(this::validateFuelType);
        Optional.ofNullable(transmission).ifPresent(this::validateTransmission);

        return carRepository.searchCars(
                price.orElse(null),
                model,
                brand,
                fuelType,
                regDate.orElse(null),
                transmission,
                remarks.orElse(null));

    }



    private void validateModel(Model model) {
        if (!modelRepository.existsById(model.getId())) {
            throw new EntityNotFoundException("Model with ID " + model.getId() + " not found.");
        }
    }

    private void validateBrand(Brand brand) {
        if (!brandRepository.existsById(brand.getId())) {
            throw new EntityNotFoundException("Brand with ID " + brand.getId() + " not found.");
        }
    }

    private void validateFuelType(FuelType fuelType) {
        if (!fuelTypeRepository.existsById(fuelType.getId())) {
            throw new EntityNotFoundException("FuelType with ID " + fuelType.getId() + " not found.");
        }
    }

    private void validateTransmission(Transmission transmission) {
        if (!transmissionRepository.existsById(transmission.getId())) {
            throw new EntityNotFoundException("Transmission with ID " + transmission.getId() + " not found.");
        }
    }

}
