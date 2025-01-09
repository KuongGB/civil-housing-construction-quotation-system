package com.example.system.repository.combo;

import com.example.system.dto.combodto.ComboResponseDto;
import com.example.system.model.combo.ComboBuilding;
import com.example.system.model.combo.ComboDetail;
import com.example.system.model.combo.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComboDetailRepository extends JpaRepository<ComboDetail, Long> {
    List<ComboDetail> findByComboBuilding(ComboBuilding comboBuilding);

    @Query("SELECT SUM(cd.material.unitPrice) FROM ComboDetail cd WHERE cd.comboBuilding = :comboBuilding")
    Long calculateTotalUnitPriceByComboBuilding(@Param("comboBuilding") ComboBuilding comboBuilding);

    @Query("SELECT cd FROM ComboDetail cd WHERE cd.comboBuilding.comboBuildingName = :comboBuildingName")
    List<ComboDetail> findAllByComboBuildingName(@Param("comboBuildingName")String comboBuildingName);

    @Query("SELECT cd FROM ComboDetail cd WHERE cd.comboBuilding.comboBuildingId = :comboBuildingId")
    List<ComboDetail> findAllByComboBuildingId(@Param("comboBuildingId")Long comboBuildingId);

    ComboDetail findComboDetailByComboBuildingAndMaterial_MaterialType(ComboBuilding comboBuilding, MaterialType materialType);
}
