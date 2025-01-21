package com.forohub.challenge.domain.topico;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String curso;
    private String titulo;
    private LocalDateTime fecha;

    public Topico(DatosRegistroTopico datosRegistroTopico){
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.curso();
        this.titulo = datosRegistroTopico.titulo();
        this.fecha = LocalDateTime.now();
    }


    public Topico(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void actualizarTopico(@Valid DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        this.fecha = LocalDateTime.now();
    }
}

