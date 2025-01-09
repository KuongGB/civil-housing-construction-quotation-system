package com.example.system.service.combobuilding;

import com.example.system.dto.combodto.MaterialTypeDto;
import com.example.system.model.combo.MaterialType;

import java.util.List;

public interface MaterialTypeService {
    List<MaterialType> getListMaterialType();

    boolean createMaterialType(MaterialTypeDto materialType);

    boolean updateMaterialType(Long materialTypeId, MaterialTypeDto materialType);

    boolean disableMaterialType(Long materialTypeId);

    MaterialType getMaterialTypeById(Long materialTypeId);
}
