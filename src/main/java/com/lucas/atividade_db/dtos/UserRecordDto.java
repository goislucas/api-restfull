package com.lucas.atividade_db.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record UserRecordDto(Long cpf, @NotBlank String nome, @NotNull Date data_nascimento) {
}
