package com.example.system.serviceImplement;

import com.example.system.dto.combodto.ComboRequestDto;
import com.example.system.dto.combodto.ComboResponseDto;
import com.example.system.dto.combodto.MaterialTypeDto;
import com.example.system.dto.combodto.MaterialTypeOfComboDto;
import com.example.system.model.combo.ComboBuilding;
import com.example.system.model.combo.ComboDetail;
import com.example.system.model.combo.Material;
import com.example.system.model.combo.MaterialType;
import com.example.system.repository.combo.ComboBuildingRepository;
import com.example.system.repository.combo.ComboDetailRepository;
import com.example.system.service.combobuilding.ComboBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ComboBuildingServiceImp implements ComboBuildingService {
    private final ComboDetailRepository comboDetailRepository;
    private final ComboBuildingRepository comboBuildingRepository;
@Override
public List<ComboResponseDto> getListCombo() {
    List<ComboResponseDto> listComboResponse = new ArrayList<>();

    List<ComboDetail> detailList = comboDetailRepository.findAll();

    // Duyệt qua danh sách ComboDetail và tạo các ComboResponseDto
    for (ComboDetail comboDetail : detailList) {
        ComboBuilding comboBuilding = comboDetail.getComboBuilding();

        // Kiểm tra xem ComboBuilding đã được xử lý chưa, nếu chưa thì tạo mới một ComboResponseDto
        Optional<ComboResponseDto> optionalComboResponseDto = listComboResponse.stream()
                .filter(dto -> dto.getComboBuildingId().equals(comboBuilding.getComboBuildingId()))
                .findFirst();

        ComboResponseDto comboResponseDto;
        if (optionalComboResponseDto.isPresent()) {
            comboResponseDto = optionalComboResponseDto.get();
        } else {
            comboResponseDto = new ComboResponseDto();
            comboResponseDto.setComboBuildingId(comboBuilding.getComboBuildingId());
            comboResponseDto.setComboBuildingName(comboBuilding.getComboBuildingName());
            comboResponseDto.setUnitPrice(comboBuilding.getUnitPrice());
            comboResponseDto.setStatus(comboBuilding.isStatus());
            comboResponseDto.setType(comboBuilding.getType());
            comboResponseDto.setMaterialTypeOfComboDto(new ArrayList<>()); // Khởi tạo danh sách materialTypeOfComboDto
            listComboResponse.add(comboResponseDto);
        }

        // Lấy hoặc tạo MaterialTypeOfComboDto cho ComboResponseDto hiện tại
        Material material = comboDetail.getMaterial();
        MaterialType materialType = material.getMaterialType();
        MaterialTypeDto materialTypeDto = new MaterialTypeDto(materialType.getMaterialTypeId(), materialType.getTypeName(), materialType.isStatus());
        MaterialTypeOfComboDto materialTypeOfComboDto = comboResponseDto.getMaterialTypeOfComboDto().stream()
                .filter(dto -> dto.getMaterialTypeDto().getMaterialTypeId().equals(materialType.getMaterialTypeId()))
                .findFirst()
                .orElseGet(() -> {
                    MaterialTypeOfComboDto newDto = new MaterialTypeOfComboDto(materialTypeDto, new ArrayList<>());
                    comboResponseDto.getMaterialTypeOfComboDto().add(newDto);
                    return newDto;
                });

        // Thêm Material vào danh sách materialList của MaterialTypeOfComboDto
        materialTypeOfComboDto.getMaterialList().add(material);
    }
    return listComboResponse;
}

    @Override
    public List<ComboResponseDto> getComboBuildingByType(Long type) {
        List<ComboResponseDto> result = new ArrayList<>();
        for (ComboResponseDto cr: getListCombo()) {
            if(cr.getType() == type) result.add(cr);
        }
        return result;
    }

    @Override
    public ComboBuilding createComboBuilding(ComboRequestDto comboRequestDto) {
        try{
            ComboBuilding newComboBuilding = new ComboBuilding();
            newComboBuilding.setComboBuildingName(comboRequestDto.getComboBuildingName());
            newComboBuilding.setStatus(comboRequestDto.isStatus());
            newComboBuilding.setType(comboRequestDto.getType());
            comboBuildingRepository.save(newComboBuilding);
            return newComboBuilding;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ComboBuilding> getListComboBuilding() {
        return comboBuildingRepository.findAll();
    }
}
