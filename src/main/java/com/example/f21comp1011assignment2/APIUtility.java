package com.example.f21comp1011assignment2;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APIUtility {
    /**
     * this method will read the jsonPriceData file
     * and create an ApiResponse object
     */
    public static ApiResponse getFlightJsonFile(){
        //create a GSON object
        Gson gson = new Gson();
        ApiResponse response = null;

        try(
                FileReader fileReader = new FileReader("jsonPriceData");
                JsonReader jsonReader = new JsonReader(fileReader);
                ){
            //covert json data to ApiResponse object
            response = gson.fromJson(jsonReader, ApiResponse.class);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    /**
     * this method will call the Travelpayouts API with the search conditions:
     * origin, destination, departure and return dates
     */
    public static ApiResponse getFlightFromTravelAPI(String origin, String destination, String departureDate, String returnDate) throws IOException, InterruptedException {
        String uri = "http://api.travelpayouts.com/v1/prices/cheap?token=2ee1d384fb0e5144acd54ed181cccc0b&currency=CAD&origin="+ origin
                + "&destination=" + destination + "&depart_date="+ departureDate + "&return_date=" + returnDate;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //Store the API response to a String
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        Gson gson = new Gson();
        ApiResponse apiResponse = null;

        try{
            apiResponse = gson.fromJson(jsonString, ApiResponse.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return apiResponse;
    }

    /**
     * this method will read the jsonCityCode file
     * and create an Array of CityCodeApiResponse object
     */
    public static CityCodeApiResponse[] getCityCodeJsonFile(){
        Gson gson = new Gson();
        CityCodeApiResponse[] response = null;

        try(
                FileReader fileReader = new FileReader("jsonCityCode");
                JsonReader jsonReader = new JsonReader(fileReader);
                ){
            response = gson.fromJson(jsonReader, CityCodeApiResponse[].class);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }

    /**
     * this method will read the city code from Travelpayouts API
     */
    public static CityCodeApiResponse[] getCityCodeFromTravelAPI() throws IOException, InterruptedException {
        String uri = "https://api.travelpayouts.com/data/en/cities.json?_gl=1*ky03y1*_ga*MTIyNjk0MDU1My4xNjM3Mzc5NjU1*_ga_1WLL0NEBEH*MTYzNzM4MDA3My4xLjEuMTYzNzM4MjcyNy4yNw..";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(uri)).build();

        //Store the API response to a String (add exception to method signature)
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        Gson gson = new Gson();
        CityCodeApiResponse[] cityCodeApiResponses = null;

        try {
            cityCodeApiResponses = gson.fromJson(jsonString, CityCodeApiResponse[].class);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return cityCodeApiResponses;
    }


}
