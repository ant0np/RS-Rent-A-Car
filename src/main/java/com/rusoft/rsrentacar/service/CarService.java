package com.rusoft.rsrentacar.service;

import com.rusoft.rsrentacar.domain.Car;

import java.util.List;

public interface CarService {

    Car add(Car car);
    void delete(Car car);
    void deleteById(Long carId);
    List<Car> findAll();
    Car findById(Long carId);

    Car findCarByModelAndYear(String model, String year);

}
