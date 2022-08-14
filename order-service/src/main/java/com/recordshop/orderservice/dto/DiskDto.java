package com.recordshop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskDto {
    private long id;
    private String nome;
    private Integer quantidade;
}
