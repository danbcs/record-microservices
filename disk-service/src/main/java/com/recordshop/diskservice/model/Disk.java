package com.recordshop.diskservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "disk")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Disk {
    @Id
    private String id;
    private String nome;
    private String artista;
    private String ano;
    private String estilo;
    private Integer quantidade;
}
