package com.rusoft.rsrentacar.service;

import com.rusoft.rsrentacar.domain.Car;
import com.rusoft.rsrentacar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car add(Car car) {
        return carRepository.saveAndFlush(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void deleteById(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long carId) {
        Optional<Car> opt = carRepository.findById(carId);
        return opt.orElse(null);
    }

    @Override
    public Car findCarByModelAndYear(String model, String year) {
        return carRepository.findCarByModelAndYear(model, year);
    }
}
