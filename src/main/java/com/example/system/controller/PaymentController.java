package com.example.system.controller;

import com.example.system.dto.invoicedto.InvoiceDto;
import com.example.system.model.payment.Invoice;
import com.example.system.service.payment.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    InvoiceService invoiceService;
    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> getInvoices(){
        List<Invoice> invoices = invoiceService.findAll();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/id")
    public ResponseEntity<Invoice> getInvoice(@RequestParam Long id){
        Invoice invoice = invoiceService.findById(id);
        return ResponseEntity.ok(invoice);
    }

    @PostMapping("/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDto invoiceDto, @RequestParam Long rcId){
        Invoice create = invoiceService.createInvoice(invoiceDto, rcId);
        return ResponseEntity.ok(create);
    }
}
