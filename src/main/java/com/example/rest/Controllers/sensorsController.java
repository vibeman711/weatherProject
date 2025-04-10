package com.example.rest.Controllers;

import com.example.rest.DTO.SensorDTO;
import com.example.rest.Services.SensorService;
import com.example.rest.models.Sensor;
import com.example.rest.util.SensorErrorResponse;
import com.example.rest.util.SensorNotCreatedException;
import com.example.rest.util.Validators.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class sensorsController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public sensorsController(SensorService sensorService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(convertToSensor(sensorDTO), bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                msg.append(error.getField()+" - "+error.getDefaultMessage()).append(";");
            }
            throw new SensorNotCreatedException(msg.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}

