package com.example.system.service.combobuilding;

import com.example.system.dto.combodto.MaterialDto;
import com.example.system.model.combo.Material;

import java.util.List;

public interface MaterialService {
    List<MaterialDto> getListMaterial();

    MaterialDto getById(Long materialId);
    boolean createMaterial(Long materialTypeId, MaterialDto material);

    boolean updateMaterial(Long materialId, MaterialDto material);

    boolean disableMaterial(Long materialId);

    List<Material> getListMaterialByTypeId(Long materialTypeId);
}
