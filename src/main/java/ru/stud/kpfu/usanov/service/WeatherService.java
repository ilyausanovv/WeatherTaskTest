package ru.stud.kpfu.usanov.service;

import ru.stud.kpfu.usanov.dto.WeatherDto;
import ru.stud.kpfu.usanov.model.Weather;

import java.util.List;

public interface WeatherService {
    List<WeatherDto> findAll();

    WeatherDto save(Weather weather);

    List<WeatherDto> getWeathersByCity(String city);
}