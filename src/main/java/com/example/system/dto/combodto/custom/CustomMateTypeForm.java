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
public class CustomMateTypeForm {
    private Long mateTypeId;
    private String mateTypeName;
    private List<CustomMateDto> mates;
}