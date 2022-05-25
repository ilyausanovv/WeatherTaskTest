package ru.stud.kpfu.usanov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.stud.kpfu.usanov.model.Appeal;

import java.util.List;

public interface AppealRepository extends JpaRepository<Appeal, Integer> {
    List<Appeal> getAppealsByUserId(Integer id);

    List<Appeal> getAppealsByWeatherCityIgnoreCase(String city);
}