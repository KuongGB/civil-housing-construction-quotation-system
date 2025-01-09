package com.example.system.repository.combo;

import com.example.system.model.combo.Material;
import com.example.system.model.combo.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long > {
    @Query("SELECT m FROM Material m WHERE m.materialType.materialTypeId = :materialTypeId")
    List<Material>findAllByMaterialType(@Param("materialTypeId") Long materialTypeId);

    List<Material> findByMaterialType(MaterialType materialType);

    Material findByMaterialId(Long materialId);
}
