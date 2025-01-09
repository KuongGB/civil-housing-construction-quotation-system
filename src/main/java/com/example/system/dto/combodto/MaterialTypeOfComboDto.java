package com.example.system.dto.combodto;

import com.example.system.model.combo.Material;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialTypeOfComboDto {
    MaterialTypeDto materialTypeDto;
    private List<Material> materialList;
}
