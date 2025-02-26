package com.example.carselector;

import java.io.*; // Importing Serializable class from java.io package to make the Car class serializable. Which
// means that the Car object can be converted into a sequence of bytes and stored in a file or
// sent over the network.

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarManager {
    private List<Car> carList;
    private Random random;
    private final String FILE_NAME = "cars.ser";

    public CarManager() {
        carList = new ArrayList<>();
        random = new Random();
        loadCars();
    }

    public void addCar(Car car) {
        if (car != null) {
            carList.add(car);
            saveCars();
        }
    }

    public boolean removeCar(int index) {
        if (index >= 0 && index < carList.size()) {
            carList.remove(index);
            saveCars();
            return true;
        }
        return false;
    }

    //overload removeCar method
    public boolean removeCar(Car car) {
        boolean removed = carList.remove(car);
        if (removed) {
            saveCars();
        }
        return removed;
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

    //Persistence methods

    //Save carlist to file using serialization
    public void saveCars() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(carList);
            System.out.println("Cars saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving cars: " + e.getMessage());
        }
    }

    //Load carlist from file using deserialization. If file does not exist, start with an empty list.
    @SuppressWarnings("unchecked")
    public void loadCars() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved cars found. Starting with an empty list.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            carList = (List<Car>) ois.readObject();
            System.out.println("Cars loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading cars: " + e.getMessage());   
        }
    }
}
