package menu.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private JButton btnEstudiante;
    private JButton btnProfesor;
    private JButton btnGrupo;
    private JButton btnCurso;
    private JButton btnSalir;

    public MenuFrame() {
        setTitle("Menu Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // Crear botones
        btnEstudiante = new JButton("Estudiante");
        btnProfesor = new JButton("Profesor");
        btnGrupo = new JButton("Grupo");
        btnCurso = new JButton("Curso");
        btnSalir = new JButton("Salir");

        // Añadir botones al frame
        add(btnEstudiante);
        add(btnProfesor);
        add(btnGrupo);
        add(btnCurso);
        add(btnSalir);

        // Añadir listeners a los botones
        btnEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(MenuFrame.this, "Has seleccionado Estudiante");
                MenuEstudianteFrame estFrame = new MenuEstudianteFrame();
                estFrame.setVisible(true);
            }
        });

        btnProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(MenuFrame.this, "Has seleccionado Profesor");
                // Aquí llamas al menú de profesor
                MenuProfesorFrame profFrame = new MenuProfesorFrame();
                profFrame.setVisible(true);
            }
        });

        btnGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(MenuFrame.this, "Has seleccionado Grupo");
                // Aquí llamas al menú de grupo
                MenuGrupoFrame grupoFrame = new MenuGrupoFrame();
                grupoFrame.setVisible(true);
            }
        });

        btnCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(MenuFrame.this, "Has seleccionado Curso");
                new MenuCursoFrame().setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuFrame.this, "Saliendo...");
                System.exit(0);
            }
        });
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            new MenuFrame().setVisible(true);
//        });
//    }
}
