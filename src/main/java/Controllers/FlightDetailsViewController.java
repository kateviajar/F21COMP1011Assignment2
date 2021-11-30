package Controllers;

import Utilities.APIUtility;
import Utilities.SceneChanger;
import com.example.f21comp1011assignment2.ApiResponse;
import com.example.f21comp1011assignment2.Flight;
import com.example.f21comp1011assignment2.PopularFlightInfo;
import com.example.f21comp1011assignment2.PopularFlights;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FlightDetailsViewController implements Initializable {

    @FXML
    private Label originLabel;

    @FXML
    private Label destinationLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label airlineLabel;

    @FXML
    private Label departTimeLabel;

    @FXML
    private Label flightNumLabel;

    @FXML
    private ImageView logoImageView;

    @FXML
    private Label returnTimeLabel;

    @FXML
    private ListView<PopularFlightInfo> destinationListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //hardcode
//        try {
//            ApiResponse apiResponse = APIUtility.getFlightFromAPI(false,"LAX","NYC","2021-11-27", "2021-12-12");
//            loadFlightDetails(apiResponse.getFlights().get(0));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * This method receives Flight object selected from search-view
     * and display the flight details and airline logo
     * @param flight
     * @param origin
     * @param destination
     */
    public void loadFlightDetails(Flight flight, String origin, String destination){
        originLabel.setText(origin);
        destinationLabel.setText(destination);
        airlineLabel.setText(flight.getAirline());
        flightNumLabel.setText(String.valueOf(flight.getFlightNumber()));
        departTimeLabel.setText(flight.getFlightDateTime(flight.getDepartureAt()));
        returnTimeLabel.setText(flight.getFlightDateTime(flight.getReturnAt()));
        priceLabel.setText(flight.getFormattedPrice());

        //Airline Logo url
        String url = "http://pics.avs.io/180/180/" + flight.getAirline() + ".png";

        logoImageView.setImage(new Image(url));

        //get the popular destinations by passing origin
        destinationListView.getItems().clear();
        PopularFlights popularFlights = null;
        try {
            popularFlights = APIUtility.getPopularFlightsFromAPI(origin);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        destinationListView.getItems().addAll(popularFlights.getPopularFlightsInfo());

    }

    /**
     * This mathod change scene to search view
     * @param event
     * @throws IOException
     */
    @FXML
    private void backToSearch(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "flight-search-view.fxml");
    }

}
