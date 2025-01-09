package com.example.system.service.payment;

import com.example.system.dto.invoicedto.InvoiceDto;
import com.example.system.model.payment.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice findById(Long InvoiceId);

    List<Invoice> findAll();

    Invoice createInvoice(InvoiceDto rqBody, Long rcId);
}
