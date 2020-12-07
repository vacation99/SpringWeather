package com.daniil.SpringWeather.Controllers;

import com.daniil.SpringWeather.Models.Weather;
import com.daniil.SpringWeather.Repo.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private Iterable<Weather> weathersResult;

    @Autowired
    WeatherRepo weatherRepo;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("weather", weathersResult);
        return "main";
    }

    @PostMapping("/main")
    public String getWeather(@RequestParam String city) {
        switch (city) {
            case "Москва":
            case "Лондон":
            case "Вашингтон":
            case "Ярославль":
                weathersResult = null;
                Iterable<Weather> weatherIterable = weatherRepo.findByCity(city);
                weathersResult = weatherIterable;
                break;
        }
        return "redirect:/main";
    }
}
