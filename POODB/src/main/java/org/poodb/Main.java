package org.poodb;

import javax.swing.*;
import java.awt.*;
import controller.ConexionController;
import menu.Menu;
import menu.frame.MenuFrame;
import view.ConsoleView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;


public class Main extends JFrame {
    private ConsoleView consoleView;
    private ConexionController conexionController;

    public Main() {
        setTitle("PP_POO");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("label");
        panel.add(label);

        JButton button = new JButton("Conectar a la Base de Datos");
        panel.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexionController.openConnection();
                    label.setText("Conexión establecida");
                    new MenuFrame().setVisible(true);
                } catch (Error error) {
                    label.setText("Error al conectar: " + error.getMessage());
                }
            }
        });

        getContentPane().add(panel);
        setVisible(true);
        initializeComponents();
    }

    private void initializeComponents() {
        consoleView = new ConsoleView();
        conexionController = new ConexionController(consoleView);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main();
        });
    }
}


