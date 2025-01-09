package com.example.system.repository.payment;

import com.example.system.model.blog.Blog;
import com.example.system.model.payment.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
