package com.example.system.dto.combodto.custom;

import com.example.system.dto.combodto.custom.CustomMateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomMateTypeDto {
    private Long mateTypeId;
    private String mateTypeName;
    private CustomMateDto mate;
}