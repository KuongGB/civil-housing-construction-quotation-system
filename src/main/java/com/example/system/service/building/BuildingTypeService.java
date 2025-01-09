package com.example.system.service.building;

import com.example.system.dto.buildingdto.building.TypeDto;
import com.example.system.model.building.BuildingType;

import java.util.List;

public interface BuildingTypeService {
    List<BuildingType> getList();
    BuildingType getById(Long btId);
    BuildingType createBuildingType(TypeDto dto);
    BuildingType updateBuildingType(TypeDto dto, Long typeId);
    BuildingType disableBuildingType(Long typeId);
}
