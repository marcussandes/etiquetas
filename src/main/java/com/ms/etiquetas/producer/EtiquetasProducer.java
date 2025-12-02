package com.ms.etiquetas.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class EtiquetasProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${tagservice.rabbitmq.exchange}")
    private String exchange;

    @Value("${tagservice.rabbitmq.routingkey}")
    private String routingKey;


    public void enviarEtiquetaExcluida(Long idEtiqueta) {
        Map<String, Object> mensagem = new HashMap<>();
        mensagem.put("idEtiqueta", idEtiqueta);
        mensagem.put("timestamp", LocalDateTime.now().toString());

        rabbitTemplate.convertAndSend(exchange, routingKey, mensagem);
        System.out.println("Evento enviado -> etiqueta.excluida: " + mensagem);
    }

}
