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
import com.example.system.repository.combo.MaterialRepository;
import com.example.system.service.combobuilding.ComboDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComboDetailServiceImp implements ComboDetailService {
    private final MaterialRepository materialRepository;
    private final ComboDetailRepository comboDetailRepository;
    private final ComboBuildingRepository comboBuildingRepository;

    @Override
    public boolean createComboDetail(ComboBuilding newComboBuilding, ComboRequestDto comboRequestDto) {
        try {
            ComboDetail newComboDetail;
            if (newComboBuilding != null) {
                for (Long materialId : comboRequestDto.getMaterialIdList()) {
                    Material material = materialRepository.findById(materialId)
                            .orElseThrow(
                                    () -> new IllegalStateException("material with id " + materialId + " does not exists"));
                    newComboDetail = new ComboDetail();
                    newComboDetail.setComboBuilding(newComboBuilding);
                    newComboDetail.setMaterial(material);
                    comboDetailRepository.save(newComboDetail);
                }
                Long price = comboDetailRepository.calculateTotalUnitPriceByComboBuilding(newComboBuilding);
                newComboBuilding.setUnitPrice(price);
                comboBuildingRepository.save(newComboBuilding);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateComboDetail(Long comboBuildingId, ComboRequestDto comboRequestDto) {
        try {

            List<ComboDetail> oldComboDetail = comboDetailRepository.findAllByComboBuildingId(comboBuildingId);
            comboDetailRepository.deleteAll(oldComboDetail);
            ComboBuilding comboBuilding = comboBuildingRepository.findByComboBuildingId(comboBuildingId);
            //update list material for combo
            ComboDetail updateComboDetail;
            for (Long materialId : comboRequestDto.getMaterialIdList()) {
                Material material = materialRepository.findById(materialId)
                        .orElseThrow(
                                () -> new IllegalStateException("material with id " + materialId + " does not exists"));
                updateComboDetail = new ComboDetail();
                updateComboDetail.setComboBuilding(comboBuilding);
                updateComboDetail.setMaterial(material);
                comboDetailRepository.save(updateComboDetail);
            }
            //update combobuilding
            Long price = comboDetailRepository.calculateTotalUnitPriceByComboBuilding(comboBuilding);
            comboBuilding.setUnitPrice(price);
            comboBuilding.setComboBuildingName(comboRequestDto.getComboBuildingName());
            comboBuilding.setStatus(comboRequestDto.isStatus());
            comboBuilding.setType(comboRequestDto.getType());
            comboBuildingRepository.save(comboBuilding);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

@Override
public ComboResponseDto getComboDetailById(Long comboBuildingId) {
    ComboBuilding comboBuilding = comboBuildingRepository.findByComboBuildingId(comboBuildingId);

    if (comboBuilding == null) {
        return null; // Trả về null nếu không tìm thấy ComboBuilding có tên tương ứng
    }

    ComboResponseDto comboResponseDto = new ComboResponseDto();
    comboResponseDto.setComboBuildingId(comboBuilding.getComboBuildingId());
    comboResponseDto.setComboBuildingName(comboBuilding.getComboBuildingName());
    comboResponseDto.setUnitPrice(comboBuilding.getUnitPrice());
    comboResponseDto.setStatus(comboBuilding.isStatus());
    comboResponseDto.setType(comboBuilding.getType());
    comboResponseDto.setMaterialTypeOfComboDto(new ArrayList<>());

    // Lấy danh sách tất cả các ComboDetail của ComboBuilding
    List<ComboDetail> comboDetails = comboDetailRepository.findAllByComboBuildingName(comboBuilding.getComboBuildingName());

    // Duyệt qua từng ComboDetail và thu thập thông tin về loại vật liệu
    for (ComboDetail comboDetail : comboDetails) {
        Material material = comboDetail.getMaterial();
        MaterialType materialType = material.getMaterialType();

        // Kiểm tra xem loại vật liệu đã được thêm vào danh sách hay chưa
        boolean materialTypeExists = comboResponseDto.getMaterialTypeOfComboDto().stream()
                .anyMatch(dto -> dto.getMaterialTypeDto().getMaterialTypeId().equals(materialType.getMaterialTypeId()));

        if (!materialTypeExists) {
            // Nếu loại vật liệu chưa tồn tại, thêm vào danh sách MaterialTypeOfComboDto
            MaterialTypeDto materialTypeDto = new MaterialTypeDto(materialType.getMaterialTypeId(), materialType.getTypeName(), materialType.isStatus());
            MaterialTypeOfComboDto materialTypeOfComboDto = new MaterialTypeOfComboDto(materialTypeDto, new ArrayList<>());
            comboResponseDto.getMaterialTypeOfComboDto().add(materialTypeOfComboDto);
        }

        // Thêm vật liệu vào danh sách của loại vật liệu tương ứng
        MaterialTypeOfComboDto materialTypeOfComboDto = comboResponseDto.getMaterialTypeOfComboDto().stream()
                .filter(dto -> dto.getMaterialTypeDto().getMaterialTypeId().equals(materialType.getMaterialTypeId()))
                .findFirst()
                .orElse(null); // Không thể xảy ra vì chúng ta đã đảm bảo loại vật liệu tồn tại

        if (materialTypeOfComboDto != null) {
            materialTypeOfComboDto.getMaterialList().add(material);
        }
    }

    return comboResponseDto;
}


}
