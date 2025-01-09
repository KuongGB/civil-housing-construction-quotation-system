package com.example.system.service.combobuilding;

import com.example.system.dto.combodto.ComboRequestDto;
import com.example.system.dto.combodto.ComboResponseDto;
import com.example.system.model.combo.ComboBuilding;

public interface ComboDetailService{
    boolean createComboDetail(ComboBuilding newComboBuilding, ComboRequestDto comboRequestDto);

    boolean updateComboDetail(Long comboBuildingId, ComboRequestDto comboRequestDto);

    ComboResponseDto getComboDetailById(Long comboBuildingId);
}
