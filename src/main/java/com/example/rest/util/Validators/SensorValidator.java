package com.example.rest.util.Validators;

import com.example.rest.Services.SensorService;
import com.example.rest.models.Sensor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.findByName(sensor.getName()).isPresent()){
            errors.rejectValue("name", null, "Sensor name already exists");
        }

    }
}
