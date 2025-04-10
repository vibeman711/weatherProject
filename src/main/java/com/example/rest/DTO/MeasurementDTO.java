package com.example.rest.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public class MeasurementDTO {

    @Min(-100)
    @Max(100)
    private float value;


    private Boolean raining;

    @NotEmpty

    private Map<String,String> sensor;

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

    public Map<String, String> getSensor() {
        if (sensor == null) {
            sensor = new HashMap<>();
        }
        return sensor;
    }

    public void setSensor(Map<String,String> sensor) {
        this.sensor = sensor;
    }
    public MeasurementDTO(Float value, Boolean raining, Map<String,String> sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }
    public MeasurementDTO() {}
}
