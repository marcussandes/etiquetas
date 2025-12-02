package com.ms.etiquetas.service;

import com.ms.etiquetas.dto.EtiquetasRequestPayload;
import com.ms.etiquetas.exception.ValidacaoException;
import com.ms.etiquetas.model.EtiquetasModel;
import com.ms.etiquetas.producer.EtiquetasProducer;
import com.ms.etiquetas.repository.EtiquetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtiquetasService {

    @Autowired
    EtiquetasRepository etiquetasRepository;

    @Autowired
    EtiquetasProducer etiquetasProducer;

    public List<EtiquetasModel> listarTodos(){
        return this.etiquetasRepository.findAll();
    }

    public Optional<EtiquetasModel> findById(long idEtiqueta){
        return this.etiquetasRepository.findById(idEtiqueta);
    }

    public EtiquetasModel criarEtiqueta(EtiquetasRequestPayload payload){

        if (this.etiquetasRepository.findFirstByEtiqueta(payload.etiqueta()).isPresent()){
            throw  new ValidacaoException("Esta etiqueta já existe");
        }

        EtiquetasModel novaEtiqueta = new EtiquetasModel();
        novaEtiqueta.setEtiqueta(payload.etiqueta());

        return this.etiquetasRepository.save(novaEtiqueta);
    }

    public boolean deletaEtiqueta(long id) {
        if (!etiquetasRepository.existsById(id)) {
            throw new RuntimeException("Etiqueta não encontrada");
        }
        etiquetasRepository.deleteById(id);

        etiquetasProducer.enviarEtiquetaExcluida(id);
        return false;
        }
    public Optional<EtiquetasModel> atualizarEtiqueta(long idEtiqueta, EtiquetasRequestPayload payload){

        Optional<EtiquetasModel> etiquetaOptional = this.etiquetasRepository.findById(idEtiqueta);

        if (etiquetaOptional.isEmpty()){
            return Optional.empty();
        }

        EtiquetasModel editaEtiqueta = etiquetaOptional.get();

        editaEtiqueta.setEtiqueta(payload.etiqueta());

        this.etiquetasRepository.save(editaEtiqueta);
        return Optional.of(editaEtiqueta);
    }
}




