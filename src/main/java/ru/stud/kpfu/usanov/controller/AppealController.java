package ru.stud.kpfu.usanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.kpfu.usanov.dto.AppealDto;
import ru.stud.kpfu.usanov.service.AppealService;

import java.util.List;

@RestController
public class AppealController {

    private final AppealService appealService;

    @Autowired
    public AppealController(AppealService appealService) {
        this.appealService = appealService;
    }

    @GetMapping("/appeals/{user_id}")
    public List<AppealDto> getAppealsByUserId(@PathVariable Integer user_id) {
        return appealService.getAppealsByUserId(user_id);
    }

    @GetMapping("/appeals/city/{city}")
    public List<AppealDto> getAppealsByCity(@PathVariable String city) {
        return appealService.getAppealsByWeatherCity(city);
    }
}