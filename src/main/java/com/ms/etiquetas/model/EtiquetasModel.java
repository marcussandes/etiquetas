package com.ms.etiquetas.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "etiquetas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtiquetasModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEtiqueta;

    private String etiqueta;
}
