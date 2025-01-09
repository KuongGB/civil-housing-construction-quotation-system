package com.example.system.repository.building;

import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.requestcontract.RequestContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingDetailRepository extends JpaRepository<BuildingDetail,Long> {
    List<BuildingDetail> findAllByBuilding(Building building);

    BuildingDetail findByRequestContract(RequestContract requestContract);
}