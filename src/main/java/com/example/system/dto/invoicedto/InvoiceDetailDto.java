package com.example.system.dto.invoicedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailDto {
    private InvoiceDto invoice;
    private String userName;
    private Long rcId;
}
