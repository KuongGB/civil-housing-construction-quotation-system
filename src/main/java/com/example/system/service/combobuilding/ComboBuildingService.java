package com.example.system.service.combobuilding;

import com.example.system.dto.combodto.ComboRequestDto;
import com.example.system.dto.combodto.ComboResponseDto;
import com.example.system.model.combo.ComboBuilding;

import java.util.List;

public interface ComboBuildingService {
    List<ComboResponseDto> getListCombo();

    ComboBuilding createComboBuilding(ComboRequestDto comboRequestDto);

    List<ComboBuilding> getListComboBuilding();

    List<ComboResponseDto> getComboBuildingByType(Long type);
}
