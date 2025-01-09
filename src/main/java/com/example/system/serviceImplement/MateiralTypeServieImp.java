package com.example.system.serviceImplement;

import com.example.system.dto.combodto.MaterialTypeDto;
import com.example.system.model.combo.Material;
import com.example.system.model.combo.MaterialType;
import com.example.system.repository.combo.MaterialRepository;
import com.example.system.repository.combo.MaterialTypeRepository;
import com.example.system.service.combobuilding.MaterialService;
import com.example.system.service.combobuilding.MaterialTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MateiralTypeServieImp implements MaterialTypeService {
    private final MaterialTypeRepository materialTypeRepository;

    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    MaterialService materialService;
    @Override
    public List<MaterialType> getListMaterialType() {
        return materialTypeRepository.findAll();
    }
    @Override
    public MaterialType getMaterialTypeById(Long materialTypeId) {
        return materialTypeRepository.findById(materialTypeId).orElseThrow();
    }
    @Override
    public boolean createMaterialType(MaterialTypeDto materialType) {
        try {
            MaterialType newMaterialType = new MaterialType();
            newMaterialType.setTypeName(materialType.getTypeName());
            newMaterialType.setStatus(true);
            materialTypeRepository.save(newMaterialType);
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMaterialType(Long materialTypeId, MaterialTypeDto materialType) {
        try {
            MaterialType materialTypeUpdate = materialTypeRepository.findById(materialTypeId)
                    .orElseThrow(
                            () -> new IllegalStateException("student with id " + materialTypeId + " does not exists"));
            materialTypeUpdate.setTypeName(materialType.getTypeName());
            materialTypeUpdate.setStatus(materialType.isStatus());
            materialTypeRepository.save(materialTypeUpdate);
            return true;
        }catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean disableMaterialType(Long materialTypeId) {
        try {
            MaterialType disable = materialTypeRepository.findById(materialTypeId)
                    .orElseThrow(
                            () -> new IllegalStateException("student with id " + materialTypeId + " does not exists"));
            disable.setStatus(false);
            for (Material m: materialRepository.findByMaterialType(disable)){
                materialService.disableMaterial(m.getMaterialId());
            }
            materialTypeRepository.save(disable);
            return true;
        }catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }


}
