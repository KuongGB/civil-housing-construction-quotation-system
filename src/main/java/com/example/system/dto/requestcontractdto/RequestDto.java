package com.example.system.dto.requestcontractdto;

import com.example.system.model.building.BuildingDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long requestContractId;
    private Date requestDate;
    private Double totalPrice;
    private String placeMeet;
    private Date dateMeet;
    private boolean status;

    private Long userId;
    private String userName;
    private String phone;
    private String email;

    private Long comboId;
    private String comboName;

    private BuildingDetail buildingDetail;
}
