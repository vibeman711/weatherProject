package com.example.rest.Services;

import com.example.rest.Repositories.SensorInterface;
import com.example.rest.models.Sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorInterface sensorInterface;

    @Autowired
    public SensorService(SensorInterface sensorInterface) {
        this.sensorInterface = sensorInterface;
    }
    @Transactional
    public void save(Sensor sensor) {
        sensorInterface.save(sensor);
    }
    public Optional<Sensor> findByName(String name) {
        return sensorInterface.findByName(name);
    }
    public Optional<Sensor> findById(int id) {
        return sensorInterface.findById(id);
    }
//    public List<Sensor> findSpecific(int id) {
//
//    }
}
