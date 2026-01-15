package com.aldocurso.LiterAlura;

import com.aldocurso.LiterAlura.model.RespuestaAPI;
import com.aldocurso.LiterAlura.principal.Principal;
import com.aldocurso.LiterAlura.service.ConsumoAPI;
import com.aldocurso.LiterAlura.service.ConvertirDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n------- LITERALURA -------");
        var principal = new Principal();
        principal.muestraElMenu();
    }
}
