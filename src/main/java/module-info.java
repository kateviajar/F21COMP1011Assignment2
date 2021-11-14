module com.example.f21comp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.f21comp1011assignment2 to javafx.fxml;
    exports com.example.f21comp1011assignment2;
}