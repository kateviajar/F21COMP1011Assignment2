package com.example.f21comp1011assignment2;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ApiResponse {
    private boolean success;
    private String currency;
    private HashMap<String, HashMap<String, FlightPrice>> data;

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

    public HashMap<String, HashMap<String, FlightPrice>> getData() {
        return data;
    }

    public void setData(HashMap<String, HashMap<String, FlightPrice>> data) {
        this.data = data;
    }
}
