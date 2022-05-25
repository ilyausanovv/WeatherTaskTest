package ru.stud.kpfu.usanov.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.kpfu.usanov.service.WeatherServices;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.dto.WeatherDto;
import ru.stud.kpfu.usanov.helper.JsonHelper;
import ru.stud.kpfu.usanov.model.Weather;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.repository.WeatherRepository;
import ru.stud.kpfu.usanov.service.UserService;
import ru.stud.kpfu.usanov.service.WeatherService;
import ru.stud.kpfu.usanov.service.AppealService;
import ru.stud.kpfu.usanov.model.Appeal;
import ru.stud.kpfu.usanov.model.User;
import ru.stud.kpfu.usanov.dto.AppealDto;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
public class WeatherController {

    private final WeatherServices WeatherServices = new WeatherServices();
    private final static JsonHelper jsonHelper = new JsonHelper();
    private final UserService userService;
    private final WeatherService weatherService;
    private final AppealService appealService;

    @Autowired
    public WeatherController(UserService userService, WeatherService weatherService, AppealService appealService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.appealService = appealService;
    }

    @GetMapping("/allWeather")
    public Iterable<WeatherDto> getAll() {
        return weatherService.findAll();
    }

    @GetMapping("/history/weather/{city}")
    public List<WeatherDto> geteatherByCity(@PathVariable String city) {
        return weatherService.getWeathersByCity(city);
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city, @RequestParam Optional<String> email)
            throws IOException {
        String unwrappedEmail = email.orElse("non@mail.ru");
        User user = userService.getByEmail(unwrappedEmail);

        if (user != null) {
            String result = WeatherServices.get(city.orElse("Kazan"));

            if (result != null) {
                Map<String, String> params = jsonHelper.parseJson(result);
                Weather weather = new Weather(params.get("description"), params.get("humidity"),
                        params.get("temp"), params.get("name"), unwrappedEmail);
                weatherService.save(weather);

                LocalDateTime dateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

                Appeal appeal = new Appeal(dateTime.format(formatter), weather, user);
                appealService.save(appeal);
                return result;
            } else {
                return "This city is not found!";
            }
        } else {
            return "null";
        }
    }
}