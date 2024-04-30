package com.example.bccrssv2;

public class WeatherContent {
    private String name;
    private String date;
    private String weatherDescription;
    private Object minTemperature;

    private Object maxTemperature;
    private String image;

    public WeatherContent(String date, String weatherDescription, int minTemperature, int maxTemperature,String image) {
        this.date = date;
        this.weatherDescription = weatherDescription;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.image = image;
    }
    public WeatherContent(String name,String date, String weatherDescription, Object minTemperature, Object maxTemperature,String image) {
        this.name = name;
        this.date = date;
        this.weatherDescription = weatherDescription;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Object getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Object getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
