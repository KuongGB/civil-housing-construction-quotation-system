package com.example.system.serviceImplement;

import com.example.system.dto.dashboarddto.DashboardDto;
import com.example.system.dto.dashboarddto.NumOfBuidingChoice;
import com.example.system.model.building.Building;
import com.example.system.model.building.BuildingDetail;
import com.example.system.model.requestcontract.RequestContract;
import com.example.system.repository.building.BuildingDetailRepository;
import com.example.system.repository.building.BuildingRepository;
import com.example.system.repository.combo.MaterialRepository;
import com.example.system.repository.requestcontract.RequestContractRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DashBoardServiceImp implements DashBoardService {
    @Autowired
    MaterialRepository materialRepository;
    @Autowired
    RequestContractRepository requestContractRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    BuildingDetailRepository buildingDetailRepository;
    @Override
    public DashboardDto getNumbers() {
        try{
            DashboardDto dashboardDto = new DashboardDto();
            dashboardDto.setBuildingCount(Long.valueOf(buildingRepository.findAll().size()));
            dashboardDto.setMaterialCount(Long.valueOf(materialRepository.findAll().size()));
            dashboardDto.setRequestContractCount(Long.valueOf(requestContractRepository.findAll().size()));
            dashboardDto.setUserCount(Long.valueOf(userRepository.findAll().size()));
            List<NumOfBuidingChoice> listBuildingChoice = new ArrayList<>();


            Set<Long> uniqueBuildingIds = new HashSet<>();
            List<BuildingDetail> buildingDetails = buildingDetailRepository.findAll();
            // Lặp qua danh sách và chỉ thêm các mục có item_id duy nhất vào danh sách đã lọc
            for (BuildingDetail b : buildingDetails) {
                uniqueBuildingIds.add(b.getBuilding().getBuildingId());
            }
            NumOfBuidingChoice numOfBuidingChoice;
            for (Long i:uniqueBuildingIds) {
                Building building = buildingRepository.findById(i).orElseThrow();
                numOfBuidingChoice = new NumOfBuidingChoice();
                numOfBuidingChoice.setQuantity(requestContractRepository.findAllSameBuilding(i).size());
                numOfBuidingChoice.setBuildingName(building.getBuildingName());
                listBuildingChoice.add(numOfBuidingChoice);
            }
            dashboardDto.setListBuildingChoice(listBuildingChoice);
            return dashboardDto;
        }catch (Exception e){
            return null;
        }
    }
}
