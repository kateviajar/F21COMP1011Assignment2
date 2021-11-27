package Controllers;

import Utilities.APIUtility;
import Utilities.SceneChanger;
import com.example.f21comp1011assignment2.ApiResponse;
import com.example.f21comp1011assignment2.CityCodeApiResponse;
import com.example.f21comp1011assignment2.Flight;
import javafx.event.ActionEvent;
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

        //set the listView and detailsButton invisible before searching
        setFlightFound(false,false);

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
                        fromCityComboBox.getItems().clear();
                        fromCityComboBox.setPromptText("Select a City");
                        fromCityComboBox.getItems().addAll(getCities(selectedContinent));
                    }
                });

        toContinentComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, selectedContinent) -> {
                    if (selectedContinent != null){
                        toCityComboBox.getItems().clear();
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
                returnDatePicker.setValue(oldDate);
                messageLabel.setText("Please select after the departure date");
            }
            else {
                messageLabel.setText("");
            }
        });

        //addListener to depart DatePicker, if the departure date cannot be later than the return date or earlier than current date
        departDatePicker.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if(newDate.compareTo(returnDatePicker.getValue()) > 0){
                //if the departure date is later than the return date, adjust the return date
                returnDatePicker.setValue(newDate);
                messageLabel.setText("Please check departure/return date");
            }
            else if(newDate.compareTo(LocalDate.now()) < 0){
                departDatePicker.setValue(oldDate);
                messageLabel.setText("cannot select ast dates");
            }
            else {
                messageLabel.setText("");
            }
        });

        //addListener to the list view, if a flight is selected, show details button
        flightListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldFlight, selectedFlight) -> {
                    setFlightFound(true, true);
                });
    }

    /**
     * This method will get search result and display in the List View if any
     */
    @FXML
    private void getSearchFlights() throws IOException, InterruptedException {
        //clear the list view first
        flightListView.getItems().clear();

        //get search terms
        String origin = fromCityComboBox.getSelectionModel().getSelectedItem();
        String destination = toCityComboBox.getSelectionModel().getSelectedItem();
        String departureDate = departDatePicker.getValue().toString();
        String returnDate = returnDatePicker.getValue().toString();

        ApiResponse apiResponse = APIUtility.getFlightFromAPI(nonStopCheckBox.isSelected(),origin,destination,departureDate,returnDate);

        if (apiResponse.getData().isEmpty()){
            //flight isn't found
            messageLabel.setText("Flight is not found");
            setFlightFound(false, false);
        }
        else{
            flightListView.getItems().addAll(apiResponse.getFlights());
            messageLabel.setText("Flexible search result:");
            setFlightFound(true, false);
        }
    }

    /**
     * This method will set visual elements to be visible or not
     * depending on the search result
     */
    private void setFlightFound(boolean flightFound, boolean flightSelected){
        flightListView.setVisible(flightFound);
        flightDetailsButton.setVisible(flightSelected);
    }


    /**
     * This method get all non-repeated and sorted continents from IATA City Code
     * some invalid data are filtered out
     */
    private TreeSet<String> getContinents(){
        TreeSet<String> continents = new TreeSet<>();
        ArrayList<String> nonValidData = new ArrayList<>();
        nonValidData.addAll(Arrays.asList("Brazil", "Etc", "US"));

        for (CityCodeApiResponse cityCode : allCityCodes){
            String[] continent = cityCode.getTimeZone().split("/");
            if (continent[0] != null && !nonValidData.contains(continent[0])){
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

        return cities;
    }

    /**
     * This method will pass the selected flight object to the loadFlightDetails method
     * through changeScenes() method, then show the flight-details-view
     */
    @FXML
    private void getFlightDetails(ActionEvent event) throws IOException {
        //get origin and destination cities
        String origin = fromCityComboBox.getSelectionModel().getSelectedItem();
        String destination = toCityComboBox.getSelectionModel().getSelectedItem();

        // get the selected flight
        Flight flight = flightListView.getSelectionModel().getSelectedItem();

        SceneChanger.changeScenes(event, "flight-details-view.fxml", flight, origin, destination);
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
