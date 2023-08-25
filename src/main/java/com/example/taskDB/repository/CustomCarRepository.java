package com.example.taskDB.repository;

import com.example.taskDB.models.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CustomCarRepository {
    List<Car> searchCars(Optional<BigDecimal> price, Optional<Model> model, Optional<Brand> brand, Optional<FuelType> fuelType, Optional<Date> regDate, Optional<Transmission> transmission,
                         Optional<String> remarks);
}
