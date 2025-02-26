package com.example.carselector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarManager {
    private List<Car> carList;
    private Random random;

    public CarManager() {
        carList = new ArrayList<>();
        random = new Random();
    }

    public void addCar(Car car) {
        if (car != null) {
            carList.add(car);
        }
    }

    public boolean removeCar(int index) {
        if (index >= 0 && index < carList.size()) {
            carList.remove(index);
            return true;
        }
        return false;
    }

    //overload removeCar method
    public boolean removeCar(Car car) {
        return carList.remove(car);
    }

    public Car getRandomCar() {
        if (carList.size() > 0) {
            int index = random.nextInt(carList.size());
            return carList.get(index);
        }
        return null;
    }

    public List<Car> getCarList() {
        return carList;
    }
}
