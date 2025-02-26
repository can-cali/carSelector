package com.example.carselector;

import java.util.List;
import java.util.Scanner;

public class App {
    private static CarManager carManager = new CarManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        System.out.println("Welcome to Car Selector App");

        while(isRunning) {
            printMenu();
            String input = scanner.nextLine().trim();
            switch(input) {
                case "1":
                    addCar();
                    break;
                case "2":
                    removeCar();
                    break;
                case "3":
                    pickRandomCar();
                    break;
                case "4":
                    listCars();
                    break;
                case "0":
                    isRunning = false;
                    System.out.println("Exiting the app. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1 - Add a new car to the list");
        System.out.println("2 - Remove a car from the list");
        System.out.println("3 - Pick a random car from the list");
        System.out.println("4 - Show all cars in the list");
        System.out.println("0 - Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addCar() {
        System.out.println("Enter the make of the car: ");
        String make = scanner.nextLine().trim();

        System.out.println("Enter the model of the car: ");
        String model = scanner.nextLine().trim();

        System.out.println("Enter the model year of the car: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        System.out.println("Enter the engine type of the car (electric, hybrid or fuel): ");
        String engineType = scanner.nextLine().trim();

        System.out.println("Enter the chassis type of the car (sedan, suv, coupe, etc): ");
        String chassisType = scanner.nextLine().trim();

        System.out.println("Enter the horsepower of the car: ");
        int horsepower = Integer.parseInt(scanner.nextLine().trim());

        Car car = new Car(make, model, year, engineType, chassisType, horsepower);
        carManager.addCar(car);
        System.out.println("Car added: " + car + " successfully!");
    }

    private static void removeCar() {
        listCars();
        System.out.println("Enter the index of the car to remove: ");
        int index = Integer.parseInt(scanner.nextLine().trim());
        if (carManager.removeCar(index - 1)) {
            System.out.println("Car removed successfully!");
        } else {
            System.out.println("Invalid index. Car not removed.");
        }
    } 

    private static void pickRandomCar() {
        Car randomCar = carManager.getRandomCar();
        if (randomCar != null) {
            System.out.println("Random car picked: " + randomCar);
        } else {
            System.out.println("No cars in the list to pick from.");
        }
    }

    private static void listCars() {
        List<Car> carList = carManager.getCarList();
        if (carList.isEmpty()) {
            System.out.println("No cars in the list");
        } else {
            System.out.println("Cars in the list:");
            for (int i = 0; i < carList.size(); i++) {
                System.out.println((i + 1) + ". " + carList.get(i));
            }
        }
    }
}
