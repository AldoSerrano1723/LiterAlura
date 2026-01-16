package com.aldocurso.LiterAlura.principal;

import com.aldocurso.LiterAlura.model.*;
import com.aldocurso.LiterAlura.service.ConsumoAPI;
import com.aldocurso.LiterAlura.service.ConvertirDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Principal {
    //ATRIBUTOS
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<Libro> libroList = new ArrayList<>();
    private List<Autor> autorList = new ArrayList<>();
    private String mensaje = """
            ----- MENU PRINCIPAL -----
            
            1- BUSCAR LIBRO POR TITULO
            2- LISTA DE TODOS LOS LIBROS
            3- LISTA DE AUTORES
            4- SALIR
            
            INGRESE EL NUMERO DE UNA DE LAS OPCIONES:
            """;

    //METODOS
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
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
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

    public void mostrarAutores() {
        System.out.println("----- LISTA DE AUTORES -----");
        autorList.forEach(System.out::println);
    }

    public void mostrarLibros() {
        System.out.println("----- LISTA DE LIBROS -----");
        libroList.forEach(System.out::println);
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
            Optional<DatosAutor> autorOptional = libroBuscado.get().autor().stream()
                    .findFirst();
            if (autorOptional.isPresent()){
                var libroEncontrado = new Libro(libroBuscado.get());
                var autorEcontrado = new Autor(autorOptional.get());

                libroList.add(libroEncontrado);
                autorList.add(autorEcontrado);

                System.out.println("\n--- LIBRO ECONTRADO ---");
                System.out.println("LOS DATOS DEL LIBRO SON: ");
                System.out.println(libroEncontrado);
                System.out.println("\n");
            }
        }else {
            System.out.println("\n***** NO ESTA EL LIBRO *****");
            System.out.println("\n");
        }
    }


//    public void top10Libros(){
//        System.out.println("\n--- TOP 10 LIBROS MAS DESCARGADOS ---");
//        RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(URL_BASE), RespuestaAPI.class);
//        respuestaAPI.listaDeLibros().stream()
//                .sorted(Comparator.comparing(DatosLibro::numeroDeDescargas).reversed())
//                .map(l -> l.titulo().toUpperCase())
//                .limit(10)
//                .forEachOrdered(System.out::println);
//        System.out.println("\n");
//    }
//
//    public void mostrarEstadisticas(){
//        System.out.println("\n--- ESTADISTICAS ---");
//        RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(URL_BASE), RespuestaAPI.class);
//        DoubleSummaryStatistics est = respuestaAPI.listaDeLibros().stream()
//                .collect(Collectors.summarizingDouble(DatosLibro::numeroDeDescargas));
//        System.out.println("MEDIA DE LAS DESCARGAS: " + est.getAverage());
//        System.out.println("LIBRO CON MAYOR DESCARGA: " + est.getMax());
//        System.out.println("LIBRO CON MENOR DESCARGA: " + est.getMin());
//        System.out.println("\n");
//    }
}
