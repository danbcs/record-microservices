package com.recordshop.orderservice.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_pedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long idCliente;
    private String documentoCliente;
    private String nomeCliente;
    private Long idDisco;
    private String nomeDisco;
    private Integer quantidadeDisco;
    private Float valorDisco;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dataHora;
}
