package menu.frame;

import javax.swing.*;
import java.awt.*;

public class MenuProfesorFrame extends JFrame {
    public MenuProfesorFrame() {
        setTitle("Menu Profesor");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Opciones del Profesor");
        panel.add(label);

        JButton btnOption1 = new JButton("Opción 1");
        panel.add(btnOption1);

        JButton btnOption2 = new JButton("Opción 2");
        panel.add(btnOption2);

        getContentPane().add(panel);
    }
}
