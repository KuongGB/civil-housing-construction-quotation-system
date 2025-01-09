package com.example.system.dto.buildingdto.building;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBuildingDto {
    private Long buildingDetailId;
    private Double area;
    private Integer numOKitchen;
    private Integer numOBathroom;
    private Integer numOBedroom;
    private Integer numOFloor;
    private boolean hasTunnel;
    private int status;
}
