package com.example.system.service.building;

import com.example.system.dto.buildingdto.building.BuildingDto;
import com.example.system.model.building.Building;

import java.util.List;

public interface BuildingService {
    List<Building> findAll();
    Building findByBuildingId(Long id);
    Building createBuilding(BuildingDto dto);
    Building updateBuilding(BuildingDto dto);
    Building disableBuilding(Long buildingId);
}
