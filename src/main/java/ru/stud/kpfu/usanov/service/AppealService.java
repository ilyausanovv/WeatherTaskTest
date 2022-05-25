package ru.stud.kpfu.usanov.service;

import ru.stud.kpfu.usanov.dto.AppealDto;
import ru.stud.kpfu.usanov.model.Appeal;

import java.util.List;

public interface AppealService {

    AppealDto save(Appeal appeal);

    List<AppealDto> getAppealsByUserId(Integer id);

    List<AppealDto> getAppealsByWeatherCity(String city);
}