package com.example.f21comp1011assignment2;

import java.io.IOException;

public class MainTest {
    public static void main(String[] args) {
//        ApiResponse result = APIUtility.getFlightJsonFile();
//        System.out.println(result);

//        CityCodeApiResponse[] cityCodeResult = APIUtility.getCityCodeJsonFile();
        CityCodeApiResponse[] cityCodeResult = null;
        try {
            cityCodeResult = APIUtility.getCityCodeFromTravelAPI();
            System.out.println(cityCodeResult);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
