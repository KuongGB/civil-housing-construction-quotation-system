package com.example.system.dto.combodto.custom;

import com.example.system.dto.combodto.custom.CustomMateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomComboDto {
    private Long comboId;
    private String comboName;
    private List<CustomMateTypeDto> mateList;
    private Long rcId;
}