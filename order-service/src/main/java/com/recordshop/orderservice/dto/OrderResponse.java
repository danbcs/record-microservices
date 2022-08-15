package com.recordshop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private long id;
    private Long idCliente;
    private String documentoCliente;
    private String nomeCliente;
    private Long idDisco;
    private String nomeDisco;
    private Integer quantidadeDisco;
    private Float valorDisco;
    private Date dataHora;
}

