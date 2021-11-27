package Utilities;

import Controllers.FlightDetailsViewController;
import com.example.f21comp1011assignment2.Flight;
import com.example.f21comp1011assignment2.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    /**
     * This method receives selected Flight object and calls the loadFlightDetails method,
     * then show the details
     */
    public static void changeScenes(ActionEvent event, String fxmlFile, Flight flight) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());

        //Get FlightDetailsViewController to call the loadFlightDetails method
        FlightDetailsViewController controller = fxmlLoader.getController();
        controller.loadFlightDetails(flight);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
