package com.recordshop.diskservice.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_disco",uniqueConstraints={@UniqueConstraint(columnNames={"nome"})})
public class Disk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String artista;
    private String ano;
    private String estilo;
    private Integer quantidade;
    private Float valor;
}
