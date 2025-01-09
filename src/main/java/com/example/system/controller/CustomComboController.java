package com.example.system.controller;

import com.example.system.dto.combodto.custom.CustomComboDto;
import com.example.system.dto.combodto.custom.CustomDetailDto;
import com.example.system.dto.combodto.custom.CustomForm;
import com.example.system.dto.combodto.custom.CustomInfor;
import com.example.system.service.combobuilding.CustomDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom-combo")
@RequiredArgsConstructor
public class CustomComboController{
    @Autowired
    CustomDetailService customDetailService;

    @GetMapping("/get")
    public ResponseEntity<CustomComboDto> getCustomDetail(@RequestParam Long requestContractId){
        return ResponseEntity.ok(customDetailService.getCustomComboDetail(requestContractId));
    }
    @GetMapping("/get-form")
    public ResponseEntity<CustomForm> getCustomComboForm(@RequestParam Long comboId){
        return ResponseEntity.ok(customDetailService.getFormCustom(comboId));
    }
    @PostMapping("/create")
    public ResponseEntity<List<CustomDetailDto>> createCustomCombo(@RequestParam Long rcId, @RequestBody CustomInfor infor){
        return ResponseEntity.ok(customDetailService.makeCustomCombo(infor, rcId));
    }
}