package com.example.system.repository.building;

import com.example.system.model.building.Building;
import com.example.system.model.requestcontract.RequestContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
