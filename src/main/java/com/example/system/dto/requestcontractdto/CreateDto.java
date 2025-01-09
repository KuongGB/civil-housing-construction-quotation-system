package com.example.system.dto.requestcontractdto;

import com.example.system.dto.combodto.custom.CustomInfor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDto {
    private Long requestContractId;
    private Long comboId;
    private String email;
    private Long buildingDetailId;
    private CustomInfor mateIds;
}
