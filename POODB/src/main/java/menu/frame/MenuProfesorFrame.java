package menu.frame;

import controller.EstudianteController;
import controller.ProfesorController;
import model.ProfesorModel;
import view.ConsoleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuProfesorFrame extends JFrame {
    private ProfesorController profesorController;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JButton btnListar;
    private JButton btnSalir;
    private JTextArea textArea;
    public MenuProfesorFrame() {
        setTitle("Menu Profesor");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificacar");
        btnEliminar = new JButton("Eliminar");
        btnConsultar = new JButton("Consultar");
        btnListar = new JButton("Lista de profesores");
        btnSalir = new JButton("Salir al menu de inicio");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnListar);
        panelBotones.add(btnSalir);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(panelBotones, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProfesor();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProfesor();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProfesor();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarProfesor();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProfesor();
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        initializeComponents();
    }

    private void initializeComponents() {
        ConsoleView consoleView = new ConsoleView();
        profesorController = new ProfesorController(consoleView);
    }

    private void agregarProfesor() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre");
        String identificacion = JOptionPane.showInputDialog(this, "Identificación:");
        String email = JOptionPane.showInputDialog(this, "Email");
        String dpto = JOptionPane.showInputDialog(this, "Departamento");

        int estado;
        try {
            estado = Integer.parseInt(JOptionPane.showInputDialog(this, "Estado (0 = Inactivo, 1 = Activo)"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Estado no válido. Debe ser 0 o 1.");
            return;
        }
        ProfesorModel profesor = new ProfesorModel(nombre, identificacion, email, dpto, estado);
        profesorController.agregarProfesor(profesor);
        textArea.setText(profesor.toString());
    }

    private void modificarProfesor() {
        String idProfeStr = JOptionPane.showInputDialog(this, "Identificación del profesor a modificar:");
        int profesorID = Integer.parseInt(idProfeStr);
        ProfesorModel profesor = profesorController.consultarProfesor(profesorID);

        if (profesor == null) {
            JOptionPane.showMessageDialog(this, "Profesor no encontrado");
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo Nombre (Ingrese 'N/A' para mantener el dato actual)", profesor.getNombre());
        if (!nuevoNombre.equals("N/A")) {
            profesor.setNombre(nuevoNombre);
        }

        String nuevoEmail = JOptionPane.showInputDialog(this, "Nuevo Email (Ingrese 'N/A' para mantener el dato actual)", profesor.getEmail());
        if (!nuevoEmail.equals("N/A")) {
            profesor.setEmail(nuevoEmail);
        }

        String nuevoDpto = JOptionPane.showInputDialog(this, "Nuevo Departamento (Ingrese 'N/A' para mantener el dato actual)", profesor.getDepartamento());
        if (!nuevoDpto.equals("N/A")) {
            profesor.setDepartamento(nuevoDpto);
        }

        String nuevoEstadoStr = JOptionPane.showInputDialog(this, "Nuevo Estado (0 = Inactivo, 1 = Activo) (Ingrese 'N/A' para mantener el dato actual)", profesor.getEstado());
        if (!nuevoEstadoStr.equals("N/A")) {
            int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
            profesor.setEstado(nuevoEstado);
        }

        profesorController.modificarProfesor(profesor);
        textArea.setText(profesor.toString());
        //textArea.append("Profesor modificado exitosamente.\n");
    }

    private void eliminarProfesor() {
        String idProfeStr = JOptionPane.showInputDialog(this, "Identificación del profesor a modificar:");
        int profesorID = Integer.parseInt(idProfeStr);
        boolean eliminado = profesorController.eliminarProfesor(profesorID);
        if (eliminado) {
            textArea.append("Estudiante eliminado exitosamente.\n");
        } else {
            textArea.append("Estudiante no eliminado.\n");
        }
    }

    private void consultarProfesor() {
        String idProfeStr = JOptionPane.showInputDialog(this, "Identificación del profesor a modificar:");
        int profesorID = Integer.parseInt(idProfeStr);

        ProfesorModel profesor = profesorController.consultarProfesor(profesorID);
        if (profesor != null) {
            textArea.append("Profesor con el ID: " + profesor.getId() + "\n" +
                    ">> Nombre: " + profesor.getNombre() + ">> Cedula: "+profesor.getIdentificacion()+">> Email: "+profesor.getEmail()+">> Estado: "+profesor.getEstado()+"\n");
        } else {
            textArea.append("Profesor no encontrado\n");
        }
    }

    private void listarProfesor() {
        List<ProfesorModel> profesores = profesorController.listarProfesores();
        if (!profesores.isEmpty()) {
            for (ProfesorModel profesor : profesores) {
                textArea.append("ID: " + profesor.getId()+ ">> Nombre: " + profesor.getNombre() + ">> Cedula: "+profesor.getIdentificacion()+">> Email: "+profesor.getEmail()+">> Estado: "+profesor.getEstado());
            }
        } else {
            textArea.append("Profesores no encontrados\n");
        }
    }

}
