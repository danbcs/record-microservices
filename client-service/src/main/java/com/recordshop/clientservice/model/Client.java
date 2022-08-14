package com.recordshop.clientservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_cliente",uniqueConstraints={@UniqueConstraint(columnNames={"documento"})})
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documento;
    private String nomeCompleto;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String email;
    private String telefone;
    private Boolean status;

}
