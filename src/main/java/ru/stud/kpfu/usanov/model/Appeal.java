package ru.stud.kpfu.usanov.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appeals")
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id")
    private Weather weather;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Appeal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appeal(String date, Weather weather, User user) {
        this.date = date;
        this.weather = weather;
        this.user = user;
    }
}