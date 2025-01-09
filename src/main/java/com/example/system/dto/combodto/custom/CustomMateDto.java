package com.example.system.dto.combodto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomMateDto {
    private Long mateId;
    private String mateName;
    private Long matePrice;
}