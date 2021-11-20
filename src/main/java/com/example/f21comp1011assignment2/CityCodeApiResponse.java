package com.example.f21comp1011assignment2;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class CityCodeApiResponse {

    @SerializedName("country_code")
    private String  countryCode;

    private String code;

    private Coordinates coordinates;

    private String name;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("name_translations")
    private HashMap<String, String> nameTranslations;

    private HashMap<String, String> cases;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public HashMap<String, String> getNameTranslations() {
        return nameTranslations;
    }

    public void setNameTranslations(HashMap<String, String> nameTranslations) {
        this.nameTranslations = nameTranslations;
    }

    public HashMap<String, String> getCases() {
        return cases;
    }

    public void setCases(HashMap<String, String> cases) {
        this.cases = cases;
    }
}
