package com.ms.etiquetas.controller;

import com.ms.etiquetas.dto.EtiquetasRequestPayload;
import com.ms.etiquetas.model.EtiquetasModel;
import com.ms.etiquetas.service.EtiquetasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetasController {

    @Autowired
    EtiquetasService etiquetasService;

    @GetMapping("/listar")
    public ResponseEntity<List<EtiquetasModel>> findAll(){
        List<EtiquetasModel> usuarios = this.etiquetasService.listarTodos();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/existe/{idEtiqueta}")
    public ResponseEntity<EtiquetasModel> findById(@PathVariable long idEtiqueta){

        Optional<EtiquetasModel> etiquetaOptional = this.etiquetasService.findById(idEtiqueta);

        if (etiquetaOptional.isPresent()) {
            return ResponseEntity.ok(etiquetaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid EtiquetasRequestPayload payload){

        try {
            EtiquetasModel novaEtiqueta = this.etiquetasService.criarEtiqueta(payload);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEtiqueta);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleta/{idEtiqueta}")
    public ResponseEntity<Void> deletaUsuario(@PathVariable long idEtiqueta){

        boolean foiDeletado = this.etiquetasService.deletaEtiqueta(idEtiqueta);

        if (foiDeletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/editar/{idEtiqueta}")
    public ResponseEntity<EtiquetasModel> editaEtiqueta(@PathVariable long idEtiqueta,
                                                        @RequestBody @Valid EtiquetasRequestPayload payload){

        Optional<EtiquetasModel> etiquetaAtualizada = this.etiquetasService.atualizarEtiqueta(idEtiqueta, payload);

        if (etiquetaAtualizada.isPresent()) {
            return ResponseEntity.ok(etiquetaAtualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
