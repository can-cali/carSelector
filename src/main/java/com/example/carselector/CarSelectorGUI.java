package com.example.carselector;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import javafx.geometry.*;

public class CarSelectorGUI extends Application {

    private CarManager carManager = new CarManager();
    private ListView<Car> listView;
    private ObservableList<Car> carObservableList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Car Selector App");

        //Creating an observable list of cars based on the cars from the manager
        carObservableList = FXCollections.observableArrayList(carManager.getCarList());
        listView = new ListView<>(carObservableList);
        listView.setPrefWidth(500);

        // Create the form to add new cars
        TextField makeField = new TextField();
        makeField.setPromptText("Make");
        
        TextField modelField = new TextField();
        modelField.setPromptText("Model");

        TextField yearField = new TextField();
        yearField.setPromptText("Year");

        TextField engineField = new TextField();
        engineField.setPromptText("Engine Type (Electric, Hybrid, Fuel)");

        TextField chassisField = new TextField();
        chassisField.setPromptText("Chassis Type (SUV, Sedan, Coupe)");

        TextField horsepowerField = new TextField();
        horsepowerField.setPromptText("Horsepower");

        Button addButton = new Button("Add Car");
        addButton.setOnAction(e -> {
            try {
                String make = makeField.getText();
                String model = modelField.getText();
                int year = Integer.parseInt(yearField.getText());
                String engine = engineField.getText();
                String chassis = chassisField.getText();
                int horsepower = Integer.parseInt(horsepowerField.getText());

                Car car = new Car(make, model, year, engine, chassis, horsepower);
                carManager.addCar(car);
                carObservableList.add(car);

                makeField.clear();
                modelField.clear();
                yearField.clear();
                engineField.clear();
                chassisField.clear();
                horsepowerField.clear();
            } catch (NumberFormatException ex) {
                showAlert("Invalid Input", "Please enter a valid number for year and horsepower");
            }
        });
        
        //Button to remove a selected car
        Button removeButton = new Button("Remove selected car");
        removeButton.setOnAction(e -> {
            Car selectedCar = listView.getSelectionModel().getSelectedItem();
            if (selectedCar != null) {
                int index = listView.getSelectionModel().getSelectedIndex();
                boolean success = carManager.removeCar(index);
                if (success) {
                    carObservableList.remove(selectedCar);
                }
                else {
                    showAlert("No Selection.", "Please select a car to remove");
                }
            }
        });

        //Button to select a random car
        Button randomButton = new Button("Pick a Random Car");
        Label randomCarLabel = new Label("Random Car: ");
        randomButton.setOnAction(e -> {
            Car randomCar = carManager.getRandomCar();
            if (randomCar != null) {
                randomCarLabel.setText("Random Car: " + randomCar.toString());
            }
            else {
                randomCarLabel.setText("No cars available");
            }
        });

        //Layout
        VBox formBox = new VBox(5, makeField, modelField, yearField, engineField, chassisField, horsepowerField, addButton);
        formBox.setPadding(new Insets(10));
        formBox.setPrefWidth(300);

        VBox listBox = new VBox(10, listView, removeButton, randomButton, randomCarLabel);
        listBox.setPadding(new Insets(10));
        
        HBox mainLayout = new HBox(10, formBox, listBox);
        mainLayout.setPadding(new Insets(10));

        Scene scene = new Scene(mainLayout, 850, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Helper method to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
