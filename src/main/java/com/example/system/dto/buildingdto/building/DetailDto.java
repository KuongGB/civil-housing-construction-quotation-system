package com.example.system.dto.buildingdto.building;

import com.example.system.model.building.BuildingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailDto {
    private BuildingDetail detail;
    private Long requestContractId;
}
