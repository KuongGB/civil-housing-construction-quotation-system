package com.example.system.repository.combo;

import com.example.system.model.combo.ComboBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboBuildingRepository extends JpaRepository<ComboBuilding, Long> {
    @Query("SELECT c FROM ComboBuilding c WHERE c.comboBuildingName = :comboBuildingName")
    ComboBuilding findByComboBuildingName(@Param("comboBuildingName")String comboBuildingName);
    @Query("SELECT c FROM ComboBuilding c WHERE c.comboBuildingId = :comboBuildingId")
    ComboBuilding findByComboBuildingId(@Param("comboBuildingId")Long comboBuildingId);
}
