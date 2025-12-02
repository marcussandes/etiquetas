package com.ms.etiquetas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EtiquetasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtiquetasApplication.class, args);
	}

}
