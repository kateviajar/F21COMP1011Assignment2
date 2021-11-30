package com.example.f21comp1011assignment2;

import java.util.HashMap;
import java.util.List;

public class PopularFlights {
    private boolean success;
    private HashMap<String, PopularFlightInfo> data;
    private String currency;

    //setters and getters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HashMap<String, PopularFlightInfo> getData() {
        return data;
    }

    public void setData(HashMap<String, PopularFlightInfo> data) {
        this.data = data;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * This method will return a list of PopularFlightInfo objects
     */
    public List<PopularFlightInfo> getPopularFlightsInfo(){
        return  data.values().stream().toList();
    }



}
