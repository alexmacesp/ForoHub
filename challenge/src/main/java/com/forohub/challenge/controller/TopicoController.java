package com.forohub.challenge.controller;


import com.forohub.challenge.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarUsuario(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                           UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(),
                topico.getMensaje(), topico.getCurso(), topico.getTitulo(),
                topico.getFecha());
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listadoTopicos(@PageableDefault(size = 5, sort = "id") Pageable paginacion){
//        return topicoRepository.findAll(paginacion);
        return ResponseEntity.ok(topicoRepository.findAll(paginacion));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico,
                                 @PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico.getId(),
                topico.getMensaje(), topico.getCurso(), topico.getTitulo(),
                topico.getFecha()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(),
                topico.getMensaje(), topico.getCurso(), topico.getTitulo(),
                topico.getFecha());
        return ResponseEntity.ok(datosTopico);
    }
}
