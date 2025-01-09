package com.example.system.dto.combodto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialTypeDto {
    private Long materialTypeId;
    private String typeName;
    private boolean status;
}
