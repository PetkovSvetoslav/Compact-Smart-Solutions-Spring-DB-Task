package com.example.taskDB.repository;

import com.example.taskDB.models.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomCarRepositoryImpl implements CustomCarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> searchCars(Optional<BigDecimal> price, Optional<Model> model,
        Optional<Brand> brand, Optional<FuelType> fuelType, Optional<Date> regDate,
         Optional<Transmission> transmission, Optional<String> remarks)  {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> cq = cb.createQuery(Car.class);
        Root<Car> carRoot = cq.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        price.ifPresent(p -> predicates.add(cb.equal(carRoot.get("price"), p)));
        model.ifPresent(m -> predicates.add(cb.equal(carRoot.get("model"), m)));
        brand.ifPresent(b -> predicates.add(cb.equal(carRoot.get("brand"), b)));
        fuelType.ifPresent(f -> predicates.add(cb.equal(carRoot.get("fuelType"), f)));
        regDate.ifPresent(r -> predicates.add(cb.equal(carRoot.get("regDate"), r)));
        transmission.ifPresent(t -> predicates.add(cb.equal(carRoot.get("transmission"), t)));
        remarks.ifPresent(r -> predicates.add(cb.like(carRoot.get("remarks"), "%" + r + "%")));

        cq.where(predicates.toArray(new Predicate[0]));
        cq.orderBy(cb.desc(carRoot.get("price")));

        return entityManager.createQuery(cq).getResultList();
    }
}

