package com.example.rest.Controllers;

import com.example.rest.DTO.MeasurementDTO;
import com.example.rest.Services.MeasurementService;
import com.example.rest.Services.SensorService;
import com.example.rest.models.Measurement;
import com.example.rest.util.MeasurementNotCreatedException;
import com.example.rest.util.MeasurementsErrorResponse;
import com.example.rest.util.Validators.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class measurementsController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final SensorService sensorService;

    @Autowired
    public measurementsController(MeasurementService measurementService, MeasurementValidator measurementValidator, SensorService sensorService) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if(bindingResult.hasErrors()) {
            StringBuilder msg = new StringBuilder();
            for(FieldError error:bindingResult.getFieldErrors()){
                msg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new MeasurementNotCreatedException(msg.toString());
        }
        measurementService.save(convertToMeasurement(measurementDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @ExceptionHandler
    private ResponseEntity<MeasurementsErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementsErrorResponse response = new MeasurementsErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        ModelMapper modelMapper = new ModelMapper();
        Measurement measurement= modelMapper.map(measurementDTO, Measurement.class);

        measurementService.enrich(measurement,measurementDTO);
        return measurement;
    }

    @GetMapping
    public ResponseEntity<List<MeasurementDTO>> getAllMeasurements() {
        List<MeasurementDTO> list = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Measurement measurement : measurementService.getMeasurements()){
            MeasurementDTO measurementDTO= modelMapper.map(measurement, MeasurementDTO.class);
            measurementDTO.getSensor().put("name", sensorService.findById(measurement.getSensorId()).get().getName());
            list.add(measurementDTO);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.countByRainyDays();
    }
}
