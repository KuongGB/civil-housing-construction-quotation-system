package com.example.system.controller;

import com.example.system.dto.buildingdto.building.*;
import com.example.system.dto.buildingdto.pricedto.PriceDetailDto;
import com.example.system.dto.buildingdto.pricedto.RequestPriceDto;
import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.building.BuildingType;
import com.example.system.service.building.BuildingDetailService;
import com.example.system.service.building.BuildingService;
import com.example.system.service.building.BuildingTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
@RequiredArgsConstructor
public class BuildingController{

    @Autowired
    BuildingService  buildingService;
    @Autowired
    BuildingDetailService buildingDetailService;
    @Autowired
    BuildingTypeService buildingTypeService;

    //Building Type Controller
    @GetMapping("/type/list")
    public ResponseEntity<List<BuildingType>> getBuildingTypes(){
        return ResponseEntity.ok(buildingTypeService.getList());
    }
    @GetMapping("/type/get")
    public ResponseEntity<BuildingType> getBuildingTypeById(@RequestParam Long buildingTypeId){
        return ResponseEntity.ok(buildingTypeService.getById(buildingTypeId));
    }
    @PostMapping("/type/create")
    public ResponseEntity<BuildingType> createBuildingType(@RequestBody TypeDto dto){
        return ResponseEntity.ok(buildingTypeService.createBuildingType(dto));
    }
    @PutMapping("/type/update")
    public ResponseEntity<BuildingType> updateBuildingType(@RequestBody TypeDto dto, @RequestParam Long typeId){
        return ResponseEntity.ok(buildingTypeService.updateBuildingType(dto, typeId));
    }
    @PutMapping("/type/disable")
    public ResponseEntity<BuildingType> disableBuildingType(@RequestParam Long typeId){
        return ResponseEntity.ok(buildingTypeService.disableBuildingType(typeId));
    }

    //Building Controller
    @GetMapping("/list")
    public ResponseEntity<List<Building>> getBuildings(){
        return ResponseEntity.ok(buildingService.findAll());
    }
    @GetMapping("/get")
    public ResponseEntity<Building> getBuildingById(@RequestParam Long buildingId){
        return ResponseEntity.ok(buildingService.findByBuildingId(buildingId));
    }
    @PostMapping("/create")
    public ResponseEntity<Building> createBuilding(@RequestBody BuildingDto dto){
        return ResponseEntity.ok(buildingService.createBuilding(dto));
    }
    @PutMapping("/update")
    public ResponseEntity<Building> updateBuilding(@RequestBody BuildingDto dto){
        return ResponseEntity.ok(buildingService.updateBuilding(dto));
    }
    @PutMapping("/disable")
    public ResponseEntity<Building> disableBuilding(@RequestParam Long buildingId){
        return ResponseEntity.ok(buildingService.disableBuilding(buildingId));
    }

    //Building Detail Controller
    @GetMapping("/detail/list")
    public ResponseEntity<List<DetailDto>> getBuildingDetails(){
        return ResponseEntity.ok(buildingDetailService.getBuildingDetails());
    }
    @GetMapping("/detail/get")
    public ResponseEntity<DetailDto> getBuildingDetail(@RequestParam Long requestContractID){
        return ResponseEntity.ok(buildingDetailService.getBuildingDetail(requestContractID));
    }
    @PostMapping("/detail/create")
    public ResponseEntity<DetailDto> createBuilding(@RequestBody RequestBuildingDto dto, @RequestParam Long buildingId){
        return ResponseEntity.ok(buildingDetailService.createBuildingDetail(buildingId, dto));
    }
    @PostMapping("/detail/start-date")
    public ResponseEntity<DetailDto> startBuilding(@RequestParam Long buildingDetailID){
        DetailDto startDate = buildingDetailService.startBuildingDetail(buildingDetailID);
        return ResponseEntity.ok(startDate);
    }

    @PostMapping("/detail/finish-date")
    public ResponseEntity<DetailDto> finishBuilding(@RequestParam Long buildingDetailID){
        DetailDto finishDate = buildingDetailService.finishBuildingDetail(buildingDetailID);
        return ResponseEntity.ok(finishDate);
    }

    @PostMapping("/detail/check-date")
    public ResponseEntity<DetailDto> checkBuilding(@RequestParam Long buildingDetailID){
        DetailDto checkDate = buildingDetailService.checkBuildingDetail(buildingDetailID);
        return ResponseEntity.ok(checkDate);
    }


    // Get each price
    @PostMapping("/price/detail")
    public ResponseEntity<PriceDetailDto> getPriceDetail(@RequestBody RequestPriceDto priceDto){
        return ResponseEntity.ok(buildingDetailService.getPriceDetail(priceDto));
    }
}
