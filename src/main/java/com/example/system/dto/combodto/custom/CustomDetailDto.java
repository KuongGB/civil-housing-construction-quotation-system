package com.example.system.dto.combodto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomDetailDto {
    private Long customId;
    private Long mateTypeId;
    private String mateTypeName;
    private CustomMateDto oldMate;
    private CustomMateDto newMate;
}