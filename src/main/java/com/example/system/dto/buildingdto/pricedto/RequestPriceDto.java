package com.example.system.dto.buildingdto.pricedto;

import com.example.system.dto.combodto.custom.CustomMateTypeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPriceDto {
    private Long buildingId;

    private Integer numOBathroom;
    private Integer numOBedroom;
    private Integer numOKitchen;
    private Integer numOFloor;
    private boolean hasTunnel;

    private Long comboId;
    private List<String> newMateIds;

    private Double area;
}
