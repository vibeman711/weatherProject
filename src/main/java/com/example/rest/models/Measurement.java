package com.example.rest.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @Min(-100)
    @Max(100)
    private float value;

    @Column(name = "raining")
    private Boolean raining;

    @Column(name = "sensor_id")
    private int sensorId;

    @Column(name = "time")
    private LocalDateTime time;

    public Measurement() {};
    public Measurement(float value, Boolean raining, int sensorId, LocalDateTime time) {
        this.value = value;
        this.raining = raining;
        this.sensorId = sensorId;
        this.time = time;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
