package com.marlon.peopleapi.rules.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marlon.peopleapi.core.database.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="pessoa")
@Builder
@Getter
@Setter
public class Pessoa extends BaseEntity implements Serializable {

    @Column(name = "nome", nullable = false)
    @NotEmpty(message = "Nome da pessoa é obrigatório.")
    @ApiModelProperty(value = "Nome da Pessoa", example = "Marlon Souza", required = true)
    private String nome;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "email")
    @ApiModelProperty(value = "Email da Pessoa")
    @Email(message = "Endereço de e-mail inválido")
    private String email;

    @Column(name = "nascimento", nullable = false)
    @NotNull(message = "Data de nascimento é obrigatória.")
    @ApiModelProperty(value = "Data de nascimento da pessoa", required = true)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate nascimento;

    @Column(name = "naturalidade")
    @ApiModelProperty(value = "Cidade natal da pessoa", example = "Cocal do sul")
    private String naturalidade;

    @Column(name = "nacionalidade")
    @ApiModelProperty(value = "Nacionalidade da pessoa", example = "Brazil")
    private String nacionalidade;

    @Column(name = "cpf", nullable = false)
    @NotEmpty(message = "CPF é obrigatório.")
    @ApiModelProperty(value = "CPF da pessoa", required = true, notes = "Não permite CPF duplicado")
    @CPF(message = "CPF Inválido")
    private String cpf;

}
