package com.example.system.controller;

import com.example.system.dto.dashboarddto.DashboardDto;
import com.example.system.model.payment.Invoice;
import com.example.system.service.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {
    @Autowired
    DashBoardService dashBoardService;
    @GetMapping("/numbers")
    public ResponseEntity<DashboardDto> getInvoices(){
        DashboardDto dashboardDto = dashBoardService.getNumbers();
        return ResponseEntity.ok(dashboardDto);
    }
}
