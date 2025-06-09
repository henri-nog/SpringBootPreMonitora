package com.premonitora.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BairroRecordDto(
        @NotBlank String nome,
        @NotNull Integer numRegistros
) {}
