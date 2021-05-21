package com.react.backend.contollers;

import com.react.backend.models.Car;
import com.react.backend.repositories.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarRepository carsRepository;

    @GetMapping
    public List<Car> cars() {
        return carsRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Car get(@PathVariable Long id) {
        return carsRepository.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car create(@RequestBody final Car car) {

        return carsRepository.saveAndFlush(car);
    }


    @RequestMapping ( value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {

        carsRepository.deleteById(id);
    }

    @RequestMapping (value = "{id}", method = RequestMethod.PUT)
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        Car existingCar = carsRepository.getById(id);
        BeanUtils.copyProperties(car, existingCar, "car_id");
        return carsRepository.saveAndFlush(car);
    }
}
