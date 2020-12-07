package com.daniil.SpringWeather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWeatherApplication.class, args);
	}

	@Bean
	public void requestToWeather() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		RestTemplate restTemplate = new RestTemplate();
		Request request = new Request();
		request.restTemplate(restTemplateBuilder);
		request.run(restTemplate);
	}
}
