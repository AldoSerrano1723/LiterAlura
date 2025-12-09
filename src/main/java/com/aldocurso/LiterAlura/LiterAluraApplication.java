package com.aldocurso.LiterAlura;

import com.aldocurso.LiterAlura.service.ConsumoAPI;
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
        System.out.println("\n----- LITERALURA -----");

        var consumoApi = new ConsumoAPI();
        String json;

        json = consumoApi.obtenerDatos("https://gutendex.com/books/?search=Quijote");
        System.out.println("\nEl json crudo es:\n");
        System.out.println(json);
    }
}
