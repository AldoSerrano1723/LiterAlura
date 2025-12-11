package com.aldocurso.LiterAlura.principal;

import com.aldocurso.LiterAlura.model.DatosLibro;
import com.aldocurso.LiterAlura.model.RespuestaAPI;
import com.aldocurso.LiterAlura.service.ConsumoAPI;
import com.aldocurso.LiterAlura.service.ConvertirDatos;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private String mensaje = """
            ----- MENU PRINCIPAL -----
            
            1- BUSCAR LIBRO POR TITULO
            2- LISTA TOP 10 LIBROS MAS DESCARGADOS
            3- ESTADISTICAS GENERALES
            4- SALIR
            
            INGRESE EL NUMERO DE UNA DE LAS OPCIONES:
            """;

    public void muestraElMenu(){
        int opcion = 0;
        while (opcion != 4){
            System.out.println(mensaje);

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    buscarTitulo();
                    break;
                case 2:
                    top10Libros();
                    break;
                case 3:
                    mostrarEstadisticas();
                    break;
                case 4:
                    System.out.println("-- ADIOS --");
                    break;
                default:
                    System.out.println("*** OPCION NO VALIDA ***");
                    break;
            }
        }
        System.out.println("FIN DEL PROGRAMA");
    }

    public void buscarTitulo(){
        System.out.println("\nINGRESE EL TITULO QUE DESA BUSCAR:");
        String titulo = sc.nextLine();
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String urlBusquedaTitulo = URL_BASE + "?search=" + tituloCodificado;
        RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(urlBusquedaTitulo), RespuestaAPI.class);
        Optional <DatosLibro> libroBuscado = respuestaAPI.listaDeLibros().stream()
                .findFirst();
        if (libroBuscado.isPresent()){
            System.out.println("\n--- LIBRO ECONTRADO ---");
            System.out.println("LOS DATOS DEL LIBRO SON: " + libroBuscado.get());
            System.out.println("\n");
        }else {
            System.out.println("\n***** NO ESTA EL LIBRO *****");
            System.out.println("\n");
        }
    }

    public void top10Libros(){
        System.out.println("\n--- TOP 10 LIBROS MAS DESCARGADOS ---");
        RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(URL_BASE), RespuestaAPI.class);
        respuestaAPI.listaDeLibros().stream()
                .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
                .limit(10)
                .forEachOrdered(System.out::println);
        System.out.println("\n");
    }

    public void mostrarEstadisticas(){
        System.out.println("\n--- ESTADISTICAS ---");
        RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(URL_BASE), RespuestaAPI.class);
        DoubleSummaryStatistics est = respuestaAPI.listaDeLibros().stream()
                .collect(Collectors.summarizingDouble(DatosLibro::numeroDeDescargas));
        System.out.println("MEDIA DE LAS DESCARGAS: " + est.getAverage());
        System.out.println("LIBRO CON MAYOR DESCARGA: " + est.getMax());
        System.out.println("LIBRO CON MENOR DESCARGA: " + est.getMin());
        System.out.println("\n");
    }
}
