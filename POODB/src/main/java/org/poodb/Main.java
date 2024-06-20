package org.poodb;

import controller.ConexionController;
import menu.Menu;
import view.ConsoleView;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.print("Hello MySQL!");

        ConsoleView consoleView = new ConsoleView();
        ConexionController conexionController = new ConexionController(consoleView);
        conexionController.openConnection();

        Menu menu = new Menu();
        menu.showMenu();

//        CursoController cursoController = new CursoController(consoleView);
//        String nombre ="Ciencias";
//        String descripcion ="Curso 2";
//        int estado = 1;
//
//        cursoController.agregarCurso(nombre,descripcion ,estado);
    }
}