package com.example.system.dto.combodto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDto {
    private Long materialId;
    private String materialName;
    private Long unitPrice;
    private Long materialTypeId;
    private String materialTypeName;
    private boolean status;
    private String unit;
}
