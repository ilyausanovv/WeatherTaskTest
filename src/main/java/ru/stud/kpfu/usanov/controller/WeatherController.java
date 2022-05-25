package ru.stud.kpfu.usanov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.kpfu.usanov.service.WeatherService;
import ru.stud.kpfu.usanov.dto.UserDto;
import ru.stud.kpfu.usanov.dto.WeatherDto;
import ru.stud.kpfu.usanov.helper.JsonHelper;
import ru.stud.kpfu.usanov.model.Weather;
import ru.stud.kpfu.usanov.repository.UserRepository;
import ru.stud.kpfu.usanov.repository.WeatherRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
public class WeatherController {

    private WeatherService WeatherService = new WeatherService();
    private final static JsonHelper jsonHelper = new JsonHelper();
    private final UserRepository userRepository;
    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherController(UserRepository userRepository, WeatherRepository weatherRepository) {
        this.userRepository = userRepository;
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/allWeather")
    public Iterable<WeatherDto> getAll() {
        return weatherRepository.findAll().stream().map(WeatherDto::fromModel).collect(Collectors.toList());
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam Optional<String> city, @RequestParam Optional<String> email)
            throws IOException {
        String unwrappedEmail = email.orElse("non@mail.ru");
        UserDto userDto = userRepository.findByEmail(unwrappedEmail);

        if (userDto != null) {
            String result = WeatherService.get(city.orElse("Kazan"));

            if (result != null) {
                Map<String, String> params = jsonHelper.parseJson(result);
                weatherRepository.save(new Weather(params.get("description"), params.get("humidity"),
                        params.get("temp"), params.get("name"), unwrappedEmail));
                return result;
            } else {
                return "This city is not found!";
            }
        } else {
            return "null";
        }
    }
}