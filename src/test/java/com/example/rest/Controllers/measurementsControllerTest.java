package com.example.rest.Controllers;

import com.example.rest.DTO.MeasurementDTO;
import com.example.rest.Services.MeasurementService;
import com.example.rest.Services.SensorService;
import com.example.rest.models.Measurement;
import com.example.rest.models.Sensor;
import com.example.rest.util.Validators.MeasurementValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class measurementsControllerTest {

    @Mock
    MeasurementService measurementService;
    @Mock
    SensorService sensorService;
    @Mock
    MeasurementValidator measurementValidator;
    @InjectMocks
    measurementsController measurementsController;

    @Test
    void getAllMeasurements_ReturnsListOfMeasurements() {
        var measurements = List.of(new Measurement(22.5F, true, 1, LocalDateTime.now()),
                new Measurement(30.0F, false, 0, LocalDateTime.now()));

        var sensor = new Sensor("sensor");
        doReturn(measurements).when(measurementService).getMeasurements();
        when(sensorService.findById(Mockito.anyInt())).thenReturn(Optional.of(sensor));

        var result = measurementsController.getAllMeasurements();

        assertNotNull(result);
        assertEquals(result.getBody().size(), measurements.size());
        assertEquals(HttpStatus.OK,result.getStatusCode());
    }

}