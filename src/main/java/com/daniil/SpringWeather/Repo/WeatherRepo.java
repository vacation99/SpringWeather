package com.daniil.SpringWeather.Repo;

import com.daniil.SpringWeather.Models.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherRepo extends CrudRepository<Weather, Long> {
    List<Weather> findByCity(String city);
}
