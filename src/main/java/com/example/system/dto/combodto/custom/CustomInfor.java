package com.example.system.dto.combodto.custom;

import com.example.system.dto.combodto.custom.CustomComboDto;
import com.example.system.dto.combodto.custom.CustomMateTypeForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomInfor {
    private List<String> newMateList;
}