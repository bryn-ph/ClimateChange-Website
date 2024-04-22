package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
    private int year;
    private float avgTemp;
    private float minTemp;
    private float maxTemp;
    private String countryName;

    // constructor
    public Data(int year, float avgTemp, float minTemp, float maxTemp, String countryName) {
        this.year = year;
        this.avgTemp = avgTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.countryName = countryName;
    }

    // getters
    public int getYear() {
        return this.year;
    }

    public float getAvgTemp() {
        return this.avgTemp;
    }

    public float getMinTemp() {
        return this.minTemp;
    }

    public float getMaxTemp() {
        return this.maxTemp;
    }

    public String getCountryName() {
        return this.countryName;
    }

    // setters
    public void setYear(int year) {
        this.year = year;
    }

    public void setAvgTemp(float avgTemp) {
        this.avgTemp = avgTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public Data() {
        // default constructor
    }

    public Data(String someParam) {
        // other constructor
    }

}
