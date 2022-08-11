package com.recordshop.diskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiskResponse {
    private String id;
    private String nome;
    private String artista;
    private String ano;
    private String estilo;
    private Integer quantidade;
}
