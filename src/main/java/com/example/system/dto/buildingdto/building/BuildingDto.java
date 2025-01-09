package com.example.system.dto.buildingdto.building;

import com.example.system.model.building.BuildingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingDto {
    private Long buildingId;
    private String buildingName;
    private Double percentPrice;
    private Long buildingTypeId;
    private boolean status;
}
