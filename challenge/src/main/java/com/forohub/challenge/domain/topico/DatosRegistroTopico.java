package com.forohub.challenge.domain.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotNull
        String mensaje,
        @NotNull
        String curso,
        @NotNull
        String titulo
){
}
