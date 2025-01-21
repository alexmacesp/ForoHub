package com.forohub.challenge.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long id,
        String mensaje,
        String curso,
        String titulo,
        LocalDateTime fecha) {
}
