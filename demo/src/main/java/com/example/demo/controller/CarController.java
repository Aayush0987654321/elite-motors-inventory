package com.example.demo.controller;

import com.example.demo.model.car;
import com.example.demo.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarRepository CarRepository;

    // This gets the list of all cars
    @GetMapping
    public List<car> getAllCars() {
        return CarRepository.findAll();
    }

    // This saves a new car to the database
    @PostMapping
    public car addCar(@RequestBody car car) {
        return CarRepository.save(car);
    }
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        CarRepository.deleteById(id);
    }
    @PutMapping("/{id}")
public car updateCar(@PathVariable Long id, @RequestBody car carDetails) {
    car car = CarRepository.findById(id).orElseThrow();
    car.setBrand(carDetails.getBrand());
    car.setModel(carDetails.getModel());
    car.setPrice(carDetails.getPrice());
    return CarRepository.save(car);
}
}