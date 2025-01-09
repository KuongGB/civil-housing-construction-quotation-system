package com.example.system.serviceImplement;

import com.example.system.dto.buildingdto.building.BuildingDto;
import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingType;
import com.example.system.repository.building.BuildingDetailRepository;
import com.example.system.repository.building.BuildingRepository;
import com.example.system.repository.building.BuildingTypeRepository;
import com.example.system.repository.combo.ComboBuildingRepository;
import com.example.system.repository.requestcontract.RequestContractRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.building.BuildingDetailService;
import com.example.system.service.building.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BuildingServiceImp implements BuildingService {

    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    BuildingDetailRepository buildingDetailRepository;
    @Autowired
    BuildingTypeRepository buildingTypeRepository;

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }
    @Override
    public Building findByBuildingId(Long id) {
        return buildingRepository.findById(id).orElseThrow();
    }
    @Override
    public Building createBuilding(BuildingDto dto) {
        Building create = new Building();
        create.setBuildingName(dto.getBuildingName());
        create.setBuildingType(buildingTypeRepository.findById(dto.getBuildingTypeId()).orElseThrow());
        create.setStatus(true);
        create.setPercentPrice(dto.getPercentPrice());
        return buildingRepository.save(create);
    }
    @Override
    public Building updateBuilding(BuildingDto dto) {
        Building update = buildingRepository.findById(dto.getBuildingId()).orElseThrow();
        update.setPercentPrice(dto.getPercentPrice());
        update.setBuildingType(buildingTypeRepository.findById(dto.getBuildingTypeId()).orElseThrow());
        update.setBuildingName(dto.getBuildingName());
        update.setStatus(dto.isStatus());
        return buildingRepository.save(update);
    }
    @Override
    public Building disableBuilding(Long buildingId) {
        Building disable = buildingRepository.findById(buildingId).orElseThrow();
        disable.setStatus(false);
        return buildingRepository.save(disable);
    }
}
