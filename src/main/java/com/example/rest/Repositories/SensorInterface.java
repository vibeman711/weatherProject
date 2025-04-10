package com.example.rest.Repositories;

import com.example.rest.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorInterface extends JpaRepository<Sensor, Integer> {
    public Optional<Sensor> findByName(String name);
}
