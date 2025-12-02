package com.ms.etiquetas.repository;

import com.ms.etiquetas.model.EtiquetasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtiquetasRepository extends JpaRepository<EtiquetasModel, Long> {

    EtiquetasModel findByEtiqueta(String etiqueta);

    Optional<EtiquetasModel> findFirstByEtiqueta(String etiqueta);
}
