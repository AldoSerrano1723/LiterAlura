package com.aldocurso.LiterAlura.principal;

import com.aldocurso.LiterAlura.model.*;
import com.aldocurso.LiterAlura.repository.AutorRepository;
import com.aldocurso.LiterAlura.repository.LibroRepository;
import com.aldocurso.LiterAlura.service.ConsumoAPI;
import com.aldocurso.LiterAlura.service.ConvertirDatos;
import jdk.swing.interop.SwingInterOpUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Principal {
    //ATRIBUTOS
    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private AutorRepository autorRepository;
    private LibroRepository libroRepository;
    private String mensaje = """
            ----- MENU PRINCIPAL -----
            
            1- BUSCAR LIBRO POR TITULO
            2- LISTA DE TODOS LOS LIBROS
            3- LISTA DE AUTORES
            4- LISTADO DE AUTORES VIVOS EN DETERMINADO AÑO
            5- LISTADO DE LIBROS POR IDIOMAS
            9- SALIR
            
            INGRESE EL NUMERO DE UNA DE LAS OPCIONES:
            """;

    //CONSTRUCTOR
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    //METODOS
    public void muestraElMenu(){
        int opcion = 0;
        while (opcion != 9){
            System.out.println(mensaje);

            try {
                opcion = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("***** ERROR: SOLO INGRESE NUMEROS ENTEROS *****");
                System.out.println("***** PRECIONE ENTER PARA CONTINUAR *****");
                sc.nextLine();
                opcion = -1;
            }
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
                    obtenerAutoresVivosEnAnio();
                    break;
                case 5:
                    obtenerLibrosPorIdioma();
                    break;
                case 9:
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

        Optional<DatosLibro> libroOptional = respuestaAPI.listaDeLibros().stream()
                .findFirst();

        if (libroOptional.isPresent()){
            Optional<Libro> libroRegistrado = libroRepository.buscarLibroPorTitulo(libroOptional.get().titulo());
            if (libroRegistrado.isEmpty()){
                //LIBRO NUEVO
                Optional<DatosAutor> autorOptional = libroOptional.get().autor().stream()
                    .findFirst();
                if (autorOptional.isPresent()){
                    Optional<Autor> autorRegistrado = autorRepository.buscarAutorPorNombre(autorOptional.get().nombre());
                    if (autorRegistrado.isEmpty()){
                        //NUEVO AUTOR Y NUEVO LIBRO
                        Autor autorNuevo = new Autor(autorOptional.get());
                        autorRepository.save(autorNuevo);
                        Libro libroNuevo = new Libro(libroOptional.get());
                        libroNuevo.setAutor(autorNuevo);
                        libroRepository.save(libroNuevo);
                        System.out.println(libroNuevo);
                    } else {
                        //NUEVO LIBRO, AUTOR YA REGISTRADO
                        Libro libroNuevo = new Libro(libroOptional.get());
                        libroNuevo.setAutor(autorRegistrado.get());
                        libroRepository.save(libroNuevo);
                        System.out.println(libroNuevo);
                    }
                }
            } else {
                System.out.println("***** EL LIBRO YA ESTA REGISTRADO *****");
            }
        }else {
            System.out.println("\n***** NO ESTA EL LIBRO *****");
            System.out.println("\n");
        }
    }

    public void mostrarLibros() {
        System.out.println("----- LISTA DE LIBROS -----");
        var listaDeLibros = libroRepository.findAll();
        listaDeLibros.forEach(System.out::println);
    }

    public void mostrarAutores() {
        System.out.println("----- LISTA DE AUTORES -----");
        var listaDeAutores = autorRepository.findAll();
        listaDeAutores.forEach(System.out::println);
    }

    public void obtenerAutoresVivosEnAnio() {
        System.out.println("INGRESA EL AÑO: ");
        try{
            Integer anio = sc.nextInt();
            var listaDeAutoresVivos = autorRepository.obtenerAutoresVivosEnAnio(anio);
            listaDeAutoresVivos.forEach(System.out::println);
        }catch (InputMismatchException e){
            System.out.println("***** ERROR: SOLO INGRESE NUMEROS ENTEROS *****");
            sc.nextLine();
        }
    }

    public void obtenerLibrosPorIdioma() {
        System.out.println("""
                IDIOMAS
                es- español
                en- ingles
                fr- frances
                pt- portugues
                
                INGRESE EL IDIOMA:
                """);
        String idioma = sc.nextLine();
        var listaDeLibrosPorIdioma = libroRepository.obtenerLibrosPorIdioma(idioma);
        if (listaDeLibrosPorIdioma.isEmpty()){
            System.out.println("*** NO HAY LIBROS EN ESE IDIOMA ***");
        } else {
            listaDeLibrosPorIdioma.forEach(System.out::println);
        }
    }
}
