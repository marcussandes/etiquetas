package com.ms.etiquetas.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth-client", url = "http://localhost:8085")
public interface AuthClient {

    @PostMapping("/auth/validate")
    boolean validate(@RequestHeader("authorization") String authorizationHeader);

    @GetMapping("/validate")
    boolean validateToken(@RequestHeader("Authorization") String authorizationHeader);
}
