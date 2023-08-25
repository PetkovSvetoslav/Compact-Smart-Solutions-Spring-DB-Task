package com.example.taskDB.repository;

import com.example.taskDB.models.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>, CustomCarRepository {

    @Query("SELECT c FROM Car c WHERE "
            + "(:price IS NULL OR c.price = :price) AND "
            + "(:model IS NULL OR c.model = :model) AND "
            + "(:brand IS NULL OR c.model.brand = :brand) AND "
            + "(:fuelType IS NULL OR c.fuelType = :fuelType) AND "
            + "(:regDate IS NULL OR c.reg_date = :regDate) AND "
            + "(:transmission IS NULL OR c.transmission = :transmission) AND "
            + "(:remarks IS NULL OR c.remarks LIKE :remarks) "
            + "ORDER BY c.price DESC")
    List<Car> searchCars(@Param("price") BigDecimal price,
                         @Param("model") Model model,
                         @Param("brand") Brand brand,
                         @Param("fuelType") FuelType fuelType,
                         @Param("regDate") Date regDate,
                         @Param("transmission") Transmission transmission,
                         @Param("remarks") String remarks);

}
