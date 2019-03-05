package com.kaidos85.demo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class CarController {

    CarRepository repository;

    @Autowired
    public CarController(CarRepository _repository){ repository = _repository;}

    @RequestMapping("/hello")
    public List<String> getList(){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        return list;
    }

    @RequestMapping("/cars")
    public List<Object> getCars(@RequestParam(value="name", defaultValue="World") String name){
        List<Object> list = new ArrayList<Object>();
        list.add(name);
        list.add(repository.findByName(name));
        return list;
    }

    @RequestMapping("/allcars")
    public List<Car> allCars(){
        return repository.findAll();
    }

    @RequestMapping(value = "/newcar", method = RequestMethod.POST)
    public Object insertCar(@RequestBody CarDTO dto){
        Car car = new Car();
        car.setName(dto.getName());
        return repository.saveAndFlush(car);
    }

}
