package ru.stud.kpfu.usanov.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherServices {

    private final static String API_KEY = "50da205a9c76cfaf41a554bc57768910";
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=";

    public String get(String city) throws IOException {
        URL myUrl = new URL(BASE_URL + city + "&appid=" + API_KEY);
        HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
        StringBuilder result = new StringBuilder();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            String input;

            while ((input = reader.readLine()) != null) {
                result.append(input);
            }

        } catch (Exception e) {
            return null;
        }

        connection.disconnect();
        return result.toString();
    }
}