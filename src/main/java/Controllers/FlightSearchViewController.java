package Controllers;

import Utilities.APIUtility;
import com.example.f21comp1011assignment2.CityCodeApiResponse;
import com.example.f21comp1011assignment2.Flight;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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

    @FXML
    private Button flightDetailsButton;

    @FXML
    private Label messageLabel;

    public static CityCodeApiResponse[] allCityCodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //get all the city codes from Travel API
        try {
            allCityCodes = APIUtility.getCityCodeFromAPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //set the listView invisible before searching
        flightListView.setVisible(false);

        //empty the messageLabel
        messageLabel.setText("");

        //configure continentComboBoxes
        fromContinentComboBox.getItems().addAll(getContinents());
        toContinentComboBox.getItems().addAll(getContinents());

        fromContinentComboBox.setPromptText("Select an Area");
        toContinentComboBox.setPromptText("Select an Area");

        //addListener to comboBoxes: if the continent is selected, then show the related cities in cityComboBox
        fromContinentComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, selectedContinent) -> {
                    if(selectedContinent != null){
                        fromCityComboBox.setPromptText("Select a City");
                        fromCityComboBox.getItems().addAll(getCities(selectedContinent));
                    }
                });

        toContinentComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, selectedContinent) -> {
                    if (selectedContinent != null){
                        toCityComboBox.setPromptText("Select a City");
                        toCityComboBox.getItems().addAll(getCities(selectedContinent));
                    }
                });

        //set depart and return DatePickers to current date
        departDatePicker.setValue(LocalDate.now());
        returnDatePicker.setValue(LocalDate.now());

        //addListener to return DatePicker, the return date cannot be earlier than the departure date or current date
        returnDatePicker.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (newDate.compareTo(departDatePicker.getValue()) < 0 || newDate.compareTo(LocalDate.now()) < 0){
                returnDatePicker.setValue(departDatePicker.getValue());
                messageLabel.setText("Please check departure/return date");
            }
            else {
                messageLabel.setText("");
            }
        });

        //addListener to depart DatePicker, if the departure date cannot be later than the return date or earlier than current date
        departDatePicker.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if(newDate.compareTo(returnDatePicker.getValue()) > 0 || newDate.compareTo(LocalDate.now()) < 0){
                departDatePicker.setValue(returnDatePicker.getValue());
                messageLabel.setText("Please check departure/return date");
            }
            else {
                messageLabel.setText("");
            }
        });

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
    private TreeSet<String> getCities(String selectedContinent){
        TreeSet<String> cities = new TreeSet<>();

        cities.addAll(Arrays.asList(allCityCodes).stream()
                .filter(city -> city.contains(selectedContinent))
                .map(city -> city.getName()+"-"+city.getCode())
                .collect(Collectors.toList()));

//        List<CityCodeApiResponse> filteredCityCodes = Arrays.asList(allCityCodes).stream()
//                .filter(city -> city.contains(selectedContinent))
//                .collect(Collectors.toList());
//
//        for (CityCodeApiResponse cityCode : filteredCityCodes){
//            cities.add(cityCode.getName()+"-"+cityCode.getCode());
//        }
        return cities;
    }

    /**
     * use addListener instead of the method
     * This method will populate the city combobox based on the continent selected
     */
    @FXML
    private void populateCities(){
        String selectedFromContinent = fromContinentComboBox.getSelectionModel().getSelectedItem();
        String selectedToContinent = toContinentComboBox.getSelectionModel().getSelectedItem();

        //check if the comboBox is selected
        if (selectedFromContinent != null){
            fromCityComboBox.setPromptText("Select a City");
            fromCityComboBox.getItems().addAll(getCities(selectedFromContinent));
        }
        if (selectedToContinent != null){
            toCityComboBox.setPromptText("Select a City");
            toCityComboBox.getItems().addAll(getCities(selectedToContinent));
        }
    }
}
