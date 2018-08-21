package com.rusoft.rsrentacar.repository;

import com.rusoft.rsrentacar.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.model=:model AND c.year=:year")
    Car findCarByModelAndYear(@Param("model") String model, @Param("year")String year);

}
