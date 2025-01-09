package com.example.system.controller;

import com.example.system.dto.buildingdto.pricedto.PriceDetailDto;
import com.example.system.dto.requestcontractdto.CreateDto;
import com.example.system.dto.requestcontractdto.RequestDto;
import com.example.system.service.requestContract.RequestContractService;
import com.example.system.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/request-contract")
@RequiredArgsConstructor
public class RequestContractController {

    @Autowired
    RequestContractService requestContractService;
    @Autowired
    UserService userService;

    @GetMapping("/request-contract/list")
    public ResponseEntity<List<RequestDto>> getRequestContracts(){
        return ResponseEntity.ok(requestContractService.findAllDto());
    }

    @GetMapping("/request-contract/get/id")
    public ResponseEntity<RequestDto> getRequestContractById(@RequestParam Long id){
        return ResponseEntity.ok(requestContractService.getById(id));
    }
    

    @GetMapping("/request-contract/list/email")
    public ResponseEntity<List<RequestDto>> getRequestContractsByEmail(@RequestParam String email){
        List<RequestDto> list = requestContractService.findDtosByEmail(email);
        return ResponseEntity.ok(list);
    }
    @PostMapping("/request-contract/create")
    public ResponseEntity<RequestDto> createRequestContract(@RequestBody CreateDto dto){
        return ResponseEntity.ok(requestContractService.createRequestContract(dto));
    }
    @PostMapping("/request-contract/comfirm")
    public ResponseEntity<RequestDto> comfirmRequestContract(@RequestParam Long requestContractId,
                                                             @RequestBody Map<String, String> requestData){
        // Lấy ngày tháng từ Map
        String dateMeetString = requestData.get("dateMeet");
        // Chuyển đổi chuỗi ngày tháng thành đối tượng Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateMeet = null;
        try {
            dateMeet = dateFormat.parse(dateMeetString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String placeMeet = requestData.get("placeMeet");
        RequestDto dto = requestContractService.confirmRequestContract(requestContractId, dateMeet, placeMeet);
        return ResponseEntity.ok(dto);
    }
    @PostMapping("/sendQuote")
    public ResponseEntity<PriceDetailDto> sendQuoteToUser(@RequestBody PriceDetailDto priceDetailDto, @RequestParam String email){
        PriceDetailDto detailPrice = requestContractService.sendQuote(priceDetailDto, email);
        return ResponseEntity.ok(detailPrice);
    }
}
