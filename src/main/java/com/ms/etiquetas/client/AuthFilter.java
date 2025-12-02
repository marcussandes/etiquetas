package com.ms.etiquetas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-client", url = "http://localhost:8083")
public interface AuthFilter {

    @PostMapping("/autenticacao/validate")
    String validarToken(@RequestParam("token") String token);
}
