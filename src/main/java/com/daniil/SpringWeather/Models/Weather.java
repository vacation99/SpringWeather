package com.daniil.SpringWeather.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Weather {
    @Id
    private String city;
    private String sunRise, SunSet, temperatureMax, temperatureMin, temperatureValue, feelsLike, speedName, speedValue, directionName, directionValue, CloudsName, weather;

    public Weather() {
    }

    public Weather(String city, String sunRise, String sunSet, String temperatureMax, String temperatureMin, String temperatureValue, String feelsLike, String speedName, String speedValue, String directionName, String directionValue, String cloudsName, String weather) {
        this.city = city;
        this.sunRise = sunRise;
        SunSet = sunSet;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.temperatureValue = temperatureValue;
        this.feelsLike = feelsLike;
        this.speedName = speedName;
        this.speedValue = speedValue;
        this.directionName = directionName;
        this.directionValue = directionValue;
        CloudsName = cloudsName;
        this.weather = weather;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return SunSet;
    }

    public void setSunSet(String sunSet) {
        SunSet = sunSet;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(String temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getSpeedName() {
        return speedName;
    }

    public void setSpeedName(String speedName) {
        this.speedName = speedName;
    }

    public String getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(String speedValue) {
        this.speedValue = speedValue;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionValue() {
        return directionValue;
    }

    public void setDirectionValue(String directionValue) {
        this.directionValue = directionValue;
    }

    public String getCloudsName() {
        return CloudsName;
    }

    public void setCloudsName(String cloudsName) {
        CloudsName = cloudsName;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
