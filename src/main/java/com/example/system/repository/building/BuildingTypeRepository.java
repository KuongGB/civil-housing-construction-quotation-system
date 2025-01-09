package com.example.system.repository.building;

import com.example.system.model.building.BuildingType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long> {
}
