package com.gvnc.search.model;

/**
 * Created by EXT01D3678 on 23.9.2016.
 */
public class Airport {

    private String iata;

    private String airport;

    private String city;

    private String state;

    private String country;

    private String latValue;

    private String longValue;

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatValue() {
        return latValue;
    }

    public void setLatValue(String latValue) {
        this.latValue = latValue;
    }

    public String getLongValue() {
        return longValue;
    }

    public void setLongValue(String longValue) {
        this.longValue = longValue;
    }
}
