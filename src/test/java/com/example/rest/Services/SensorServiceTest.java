package com.example.rest.Services;

import com.example.rest.Repositories.SensorInterface;
import com.example.rest.models.Sensor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SensorServiceTest {
    @Mock
    SensorInterface sensorInterface;
    @InjectMocks
    SensorService sensorService;

    @Test
    void save() {
        Sensor sensor = new Sensor("My Sensor");
        when(sensorInterface.save(sensor)).thenReturn(sensor);
        sensorService.save(sensor);
        verify(sensorInterface).save(sensor);
    }
}