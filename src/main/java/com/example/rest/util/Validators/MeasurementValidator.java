package com.example.rest.util.Validators;

import com.example.rest.DTO.MeasurementDTO;
import com.example.rest.Services.MeasurementService;
import com.example.rest.Services.SensorService;
import com.example.rest.models.Measurement;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class MeasurementValidator implements Validator {
    private final MeasurementService measurementService;
    private final SensorService sensorService;


    public MeasurementValidator(MeasurementService measurementService, SensorService sensorService) {
        this.measurementService = measurementService;
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if(sensorService.findByName((String) measurementDTO.getSensor().get("name")).isEmpty()) {
            errors.rejectValue("sensor", null, "Sensor does not exist");
        }
    }
}
