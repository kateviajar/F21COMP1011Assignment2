module com.example.f21comp1011assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires java.net.http;
    requires json.simple;


    opens com.example.f21comp1011assignment2 to javafx.fxml, com.google.gson;
    exports com.example.f21comp1011assignment2;
}