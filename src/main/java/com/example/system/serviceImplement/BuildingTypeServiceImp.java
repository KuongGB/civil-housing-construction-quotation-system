package com.example.system.serviceImplement;

import com.example.system.dto.buildingdto.building.TypeDto;
import com.example.system.model.building.BuildingType;
import com.example.system.repository.building.BuildingTypeRepository;
import com.example.system.service.building.BuildingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingTypeServiceImp implements BuildingTypeService {
    @Autowired
    BuildingTypeRepository buildingTypeRepository;
    @Override
    public List<BuildingType> getList() {
        return buildingTypeRepository.findAll();
    }

    @Override
    public BuildingType getById(Long btId) {
        return buildingTypeRepository.findById(btId).orElseThrow();
    }

    @Override
    public BuildingType createBuildingType(TypeDto dto) {
        BuildingType create = new BuildingType();
        create.setBuildingTypeName(dto.getBuildingName());
        create.setStatus(true);
        return buildingTypeRepository.save(create);
    }

    @Override
    public BuildingType updateBuildingType(TypeDto dto, Long typeId) {
        BuildingType update = buildingTypeRepository.findById(typeId).orElseThrow();
        update.setBuildingTypeName(dto.getBuildingName());
        update.setStatus(dto.isStatus());
        return buildingTypeRepository.save(update);
    }

    @Override
    public BuildingType disableBuildingType(Long typeId) {
        BuildingType disable = buildingTypeRepository.findById(typeId).orElseThrow();
        disable.setStatus(false);
        return buildingTypeRepository.save(disable);
    }
}
