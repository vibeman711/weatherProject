package com.example.rest.Services;

import com.example.rest.DTO.MeasurementDTO;
import com.example.rest.Repositories.MeasurementInterface;
import com.example.rest.models.Measurement;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;

import java.util.List;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementInterface measurementInterface;
    private final SensorService sensorService;

    public MeasurementService(MeasurementInterface measurementInterface, SensorService sensorService) {
        this.measurementInterface = measurementInterface;
        this.sensorService = sensorService;
    }
    @Transactional
    public void save(Measurement measurement) {
        measurementInterface.save(measurement);
    }

    public List<Measurement> getMeasurements() {
        return measurementInterface.findAll();
    }

    public int countByRainyDays(){
        return measurementInterface.countByRaining(true);
    }




    public void enrich(Measurement measurement, MeasurementDTO measurementDTO) {
        measurement.setSensorId(sensorService.findByName((String) measurementDTO.getSensor().get("name")).get().getId());
        measurement.setTime(LocalDateTime.now());
    }
}
