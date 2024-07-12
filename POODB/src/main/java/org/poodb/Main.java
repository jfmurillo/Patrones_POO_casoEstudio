package org.poodb;

import javax.swing.*;
import java.awt.*;
import controller.ConexionController;
import menu.Menu;
import view.ConsoleView;

import java.sql.SQLException;


public class Main extends JFrame{

    public Main(){
        setTitle("PP_POO");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla

        // Agregar componentes a la ventana
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("¡Hola, mundo!");
        panel.add(label);

        JButton button = new JButton("Haz clic");
        panel.add(button);

        // Agrega el panel al contenido de la ventana
        getContentPane().add(panel);

        // Mostrar la ventana
        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        System.out.print("Hello MySQL!");

        ConsoleView consoleView = new ConsoleView();
        ConexionController conexionController = new ConexionController(consoleView);
        conexionController.openConnection();

//        Menu menu = new Menu();
//        menu.showMenu();
        SwingUtilities.invokeLater(() -> {
            new Main(); // Crear y mostrar la ventana
        });
    }
}


