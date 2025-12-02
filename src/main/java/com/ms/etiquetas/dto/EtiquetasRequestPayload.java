package com.ms.etiquetas.dto;


import jakarta.validation.constraints.NotBlank;

public record EtiquetasRequestPayload(
        @NotBlank(message = "O endereço é um campo obrigatório")
        String etiqueta) {
}
