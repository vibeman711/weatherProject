package com.example.rest.Repositories;

import com.example.rest.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasurementInterface extends JpaRepository<Measurement, Integer> {
    public List<Measurement> findAll();
    public int countByRaining(Boolean raining);
}
