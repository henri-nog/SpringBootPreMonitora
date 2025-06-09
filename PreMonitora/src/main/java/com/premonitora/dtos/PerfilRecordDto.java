package com.premonitora.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PerfilRecordDto(
        @NotBlank String nome,
        @NotBlank String cpf,
        @NotNull int idade,
        @NotBlank String endereco,
        @NotBlank String senha
) {}
