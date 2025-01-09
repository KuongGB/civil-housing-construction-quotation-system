package com.example.system.serviceImplement;

import com.example.system.dto.invoicedto.InvoiceDto;
import com.example.system.model.payment.Invoice;
import com.example.system.model.requestcontract.RequestContract;
import com.example.system.repository.payment.InvoiceRepository;
import com.example.system.repository.requestcontract.RequestContractRepository;
import com.example.system.repository.user.UserRepository;
import com.example.system.service.payment.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImp implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    RequestContractRepository requestContractRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(InvoiceDto rqBody, Long rcId) {
        try{
            Invoice invoice = new Invoice();
            Double amount = Double.valueOf(rqBody.getAmount());
            invoice.setAmount(amount/100);
            invoice.setBankCode(rqBody.getBankCode());
            invoice.setBankTranNo(rqBody.getBankTranNo());
            invoice.setCardType(rqBody.getCardType());
            invoice.setOrderInfo(rqBody.getOrderInfo());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = dateFormat.parse(rqBody.getPayDate());
            invoice.setPayDate(date);
            invoice.setResponseCode(rqBody.getResponseCode());
            invoice.setTmnCode(rqBody.getTmnCode());
            invoice.setTransactionNo(rqBody.getTransactionNo());
            invoice.setTransactionStatus(rqBody.getTransactionStatus());
            invoice.setTxnRef(rqBody.getTxnRef());
            invoice.setSecureHash(rqBody.getSecureHash());

            RequestContract rq = requestContractRepository.findById(rcId).orElseThrow();
            invoice.setUser(rq.getUser());
            invoice.setRequestContract(rq);
            Invoice newInvoice = invoiceRepository.save(invoice);
            rq.setInvoice(invoice);
            if(newInvoice.getTransactionStatus().equals("00")){
                rq.setPayStatus(true);
                rq.setTotalPrice(rq.getTotalPrice()- invoice.getAmount());
            }
            requestContractRepository.save(rq);
            return newInvoice;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Invoice findById(Long id) {
        return invoiceRepository.findById(id).orElseThrow();
    }
}
