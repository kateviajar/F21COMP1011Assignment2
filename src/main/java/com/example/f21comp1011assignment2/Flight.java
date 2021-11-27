package com.example.f21comp1011assignment2;

import com.google.gson.annotations.SerializedName;

public class Flight {
    private int price;

    private String airline;

    @SerializedName("flight_number")
    private int flightNumber;

    @SerializedName("departure_at")
    private String departureAt;

    @SerializedName("return_at")
    private String returnAt;

    @SerializedName("expires_at")
    private String expiresAt;

    //setters and getters
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

    public String getDepartureAt() {
        return departureAt;
    }

    public void setDepartureAt(String departureAt) {
        this.departureAt = departureAt;
    }

    public String getReturnAt() {
        return returnAt;
    }

    public void setReturnAt(String returnAt) {
        this.returnAt = returnAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * Override the toString to display flight information
     */
    public String toString(){
        return String.format("%s-%d  Departure: %s, Return: %s --- Price $%d CAD", airline, flightNumber,
                departureAt.substring(0,10), returnAt.substring(0,10), price);
    }

    /**
     * This method will format the departure date and return date
     * as "yyyy-MM-dd   hh:mm"
     * @return
     */
    public String getFlightDateTime(String dateTime){
        return String.format("%s   %s", dateTime.substring(0,10), dateTime.substring(11, 16));
    }

    /**
     * This method will format the price
     */
    public String getFormattedPrice(){
        return String.format("$%d CAD", price);
    }

}
