package ru.stud.kpfu.usanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.usanov.model.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
}