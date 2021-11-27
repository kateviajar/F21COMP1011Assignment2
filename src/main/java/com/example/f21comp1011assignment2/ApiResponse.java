package com.example.f21comp1011assignment2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApiResponse {
    private boolean success;
    private String currency;
    private HashMap<String, HashMap<String, Flight>> data;

    //setters and getters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public HashMap<String, HashMap<String, Flight>> getData() {
        return data;
    }

    public void setData(HashMap<String, HashMap<String, Flight>> data) {
        this.data = data;
    }

    /**
     * This method will return a list of Flight objects from API response
     * @return List<Flight>
     */
    public List<Flight> getFlights(){
        //handle the null data issue, if no flights returned
        try {
            List<Flight> flights = data.values().stream()
                    .toList()
                    .get(0)
                    .values()
                    .stream()
                    .toList();
            return flights;
        } catch (ArrayIndexOutOfBoundsException e)
        {
            return null;
        }
    }
}
