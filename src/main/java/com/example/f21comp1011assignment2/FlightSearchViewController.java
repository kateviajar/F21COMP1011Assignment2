package com.example.f21comp1011assignment2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class FlightSearchViewController implements Initializable {

    @FXML
    private DatePicker departDatePicker;

    @FXML
    private ComboBox<String> fromCityComboBox;

    @FXML
    private ComboBox<String> fromContinentComboBox;

    @FXML
    private CheckBox nonStopCheckBox;

    @FXML
    private DatePicker returnDatePicker;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> toCityComboBox;

    @FXML
    private ComboBox<String> toContinentComboBox;

    @FXML
    private ListView<Flight> flightListView;

    private CityCodeApiResponse[] allCityCodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get all the city codes from Travel API
        try {
            allCityCodes = APIUtility.getCityCodeFromTravelAPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //set the listView invisible before searching
        flightListView.setVisible(false);

        //configure continentComboBoxes
        fromContinentComboBox.getItems().addAll(getContinents());
        toContinentComboBox.getItems().addAll(getContinents());

        //addListener to combobox?


    }

    /**
     * This method get all non-repeated and sorted continents from IATA City Code
     */
    private TreeSet<String> getContinents(){
        TreeSet<String> continents = new TreeSet<>();

        for (CityCodeApiResponse cityCode : allCityCodes){
            String[] continent = cityCode.getTimeZone().split("/");
            if (continent[0] != null){
                continents.add(continent[0]);
            }
        }
        return continents;
    }

    /**
     * This method get all the cities of selected continent
     */
    private TreeSet<String> getCities(String selectedArea){
        TreeSet<String> cities = new TreeSet<>();

        List<CityCodeApiResponse> filteredCityCodes = Arrays.asList(allCityCodes).stream()
                .filter(city -> city.contains(selectedArea))
                .collect(Collectors.toList());

        for (CityCodeApiResponse cityCode : filteredCityCodes){
            cities.add(cityCode.getName()+"-"+cityCode.getCode());
        }
        return cities;
    }

    /**
     * This method will populate the city combobox based on the continent selected
     */
    @FXML
    private void populateCities(){
        String selectedFromContinent = fromContinentComboBox.getSelectionModel().getSelectedItem();
        String selectedToContinent = toContinentComboBox.getSelectionModel().getSelectedItem();

        if (selectedFromContinent != null){
            fromCityComboBox.getItems().addAll(getCities(selectedFromContinent));
        }
        if (selectedToContinent != null){
            toCityComboBox.getItems().addAll(getCities(selectedToContinent));
        }
    }
}
