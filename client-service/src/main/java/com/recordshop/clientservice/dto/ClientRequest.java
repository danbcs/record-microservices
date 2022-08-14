package com.recordshop.clientservice.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {
    private Long id;
    private String documento;
    private String nomeCompleto;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private Boolean status;
}
