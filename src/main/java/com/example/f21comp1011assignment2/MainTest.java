package com.example.f21comp1011assignment2;

import Utilities.APIUtility;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
//        ApiResponse result = APIUtility.getFlightJsonFile();
//        ApiResponse result = null;
//        try {
//            result = APIUtility.getFlightFromAPI("YVR","NYC","2021-12","2021-12");
//            System.out.println(result);
//            System.out.println(result.getFlights());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        CityCodeApiResponse[] cityCodeResult = APIUtility.getCityCodeJsonFile();
//        CityCodeApiResponse[] cityCodeResult = null;
//        try {
//            cityCodeResult = APIUtility.getCityCodeFromTravelAPI();
//            System.out.println(cityCodeResult);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        try {
            PopularFlights flights = APIUtility.getPopularFlightsFromAPI("YVR");
            System.out.println(flights.getPopularFlightsInfo().get(0).getDestination());
            System.out.println(flights.getPopularFlightsInfo().get(0).getDestinationCity());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
