package com.example.system.dto.combodto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComboFormConsultantDto {
    private Long comboBuildingId;
    private String comboBuildingName;
    private double comboPrice;
}
