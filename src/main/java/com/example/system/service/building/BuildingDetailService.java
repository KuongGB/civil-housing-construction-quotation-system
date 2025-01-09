package com.example.system.service.building;

import com.example.system.dto.buildingdto.building.DetailDto;
import com.example.system.dto.buildingdto.building.RequestBuildingDto;
import com.example.system.dto.buildingdto.pricedto.PriceDetailDto;
import com.example.system.dto.buildingdto.pricedto.RequestPriceDto;
import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;

import java.util.List;

public interface BuildingDetailService {
    List<DetailDto> getBuildingDetails();
    DetailDto getBuildingDetail(Long requestContractId);
    PriceDetailDto getPriceDetail(RequestPriceDto dto);
    DetailDto createBuildingDetail(Long buildingId, RequestBuildingDto buildingDto);
    DetailDto startBuildingDetail(Long buildingDetailId);
    DetailDto checkBuildingDetail(Long buildingDetailId);
    DetailDto finishBuildingDetail(Long buildingDetailId);
}
