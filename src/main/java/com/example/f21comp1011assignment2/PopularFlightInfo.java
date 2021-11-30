package com.example.f21comp1011assignment2;

import Utilities.APIUtility;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static Controllers.FlightSearchViewController.allCityCodes;

public class PopularFlightInfo {
    private String origin;
    private String destination;
    private int price;
    private String airline;

    @SerializedName("flight_number")
    private int flightNumber;

    @SerializedName("departure_at")
    private String departureDate;

    @SerializedName("return_at")
    private String returnDate;

    private int transfers;


    //setters and getters
    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }


    /**
     * This method will get the city name from IATA json data based on the city code
     * @return
     */
    public String getDestinationCity(){
        String destinationCity = Arrays.asList(allCityCodes).stream()
                .filter(city -> city.getCode().equals(destination))
                .map(city -> city.getName() +"-"+city.getCode())
                .collect(Collectors.toList()).get(0);

        return destinationCity;
    }

    /**
     * Override the toString method to display popular flights info
     */
    public String toString(){
        return String.format("%s:  %s-%d --- Price $%dCAD", getDestinationCity(),
                airline, flightNumber, price);
    }

}
