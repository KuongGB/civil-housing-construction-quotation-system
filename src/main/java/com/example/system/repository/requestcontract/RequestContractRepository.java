package com.example.system.repository.requestcontract;

import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.requestcontract.RequestContract;
import com.example.system.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestContractRepository extends JpaRepository<RequestContract, Long>{
    List<RequestContract> findByUser(User user);

    @Query("SELECT rq FROM RequestContract rq WHERE rq.buildingDetail.building.buildingId = :buildingId")
    List<RequestContract> findAllSameBuilding(@Param("buildingId") Long buildingId);

    RequestContract findByBuildingDetail(BuildingDetail buildingDetail);
}
