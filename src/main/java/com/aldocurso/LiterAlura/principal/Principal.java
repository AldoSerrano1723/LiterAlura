package com.aldocurso.LiterAlura.principal;

import com.aldocurso.LiterAlura.model.RespuestaAPI;
import com.aldocurso.LiterAlura.service.ConsumoAPI;
import com.aldocurso.LiterAlura.service.ConvertirDatos;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

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
                    System.out.println("INGRESE EL TITULO QUE DESA BUSCAR:");
                    String titulo = sc.nextLine();
                    String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
                    String urlBusquedaTitulo = URL_BASE + "?search=" + tituloCodificado;
                    RespuestaAPI respuestaAPI = convertirDatos.obtenerDatos(consumoApi.obtenerDatos(urlBusquedaTitulo), RespuestaAPI.class);
                    System.out.println(respuestaAPI.listaDeLibros().getClass());
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("ADIOS");
                    break;
                default:
                    System.out.println("OPCION NO VALIDA ");
                    break;
            }
        }
        System.out.println("FIN DEL PROGRAMA");
    }
}
