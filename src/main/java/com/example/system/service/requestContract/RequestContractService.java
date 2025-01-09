package com.example.system.service.requestContract;

import com.example.system.dto.buildingdto.pricedto.PriceDetailDto;
import com.example.system.dto.requestcontractdto.CreateDto;
import com.example.system.dto.requestcontractdto.RequestDto;
import com.example.system.model.requestcontract.RequestContract;

import java.util.Date;
import java.util.List;

public interface RequestContractService {
    List<RequestContract> findAll();
    List<RequestDto> findAllDto();
    RequestDto getById(Long rcId);
    List<RequestDto> findDtosByEmail(String email);
    RequestDto createRequestContract(CreateDto createDto);
    RequestDto confirmRequestContract(Long rcId, Date dateMeet, String placeMeet);

    PriceDetailDto sendQuote(PriceDetailDto priceDetailDto, String email);
}
