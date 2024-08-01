package menu.frame;

import controller.CursoController;
import controller.EstudianteController;
import model.CursoModel;
import model.EstudianteModel;
import view.ConsoleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MenuEstudianteFrame extends JFrame {
    private EstudianteController estudianteController;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JButton btnListar;
    private JButton btnSalir;
    private JTextArea textArea;

    public MenuEstudianteFrame() {
        setTitle("Menu Estudiante");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1));

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificacar");
        btnEliminar = new JButton("Eliminar");
        btnConsultar = new JButton("Consultar");
        btnListar = new JButton("Lista de estudiantes");
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
                agregarEstudiante();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstudiante();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarEstudiante();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEstudiante();
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
        estudianteController = new EstudianteController(consoleView);
    }

    private void agregarEstudiante() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre");
        String identificacion = JOptionPane.showInputDialog(this, "Identificación:");
        String email = JOptionPane.showInputDialog(this, "Email");
        String fechaNacimientoStr = JOptionPane.showInputDialog(this, "Fecha de Nacimiento (dd/MM/yyyy):");

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimientoUtil;
        try {
            fechaNacimientoUtil = dateFormat.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Fecha de nacimiento no válida. Utilice el formato dd/MM/yyyy.");
            return;
        }
        SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimientoSqlStr = sqlDateFormat.format(fechaNacimientoUtil);
        java.sql.Date fechaNacimientoSql = java.sql.Date.valueOf(fechaNacimientoSqlStr);


        int estado;
        try {
            estado = Integer.parseInt(JOptionPane.showInputDialog(this, "Estado (0 = Inactivo, 1 = Activo)"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Estado no válido. Debe ser 0 o 1.");
            return;
        }

        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, fechaNacimientoSql, estado);
        estudianteController.agregarEstudiante(estudiante);
        textArea.append("Estudiante agregado exitosamente.\n");
    }

    private void modificarEstudiante() {
        String idEstStr = JOptionPane.showInputDialog(this, "Identificación del estudiante a modificar:");
        int estudianteID = Integer.parseInt(idEstStr);
        EstudianteModel estudiante = estudianteController.consultarEstudiante(estudianteID);

        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "Estudiante no encontrado.");
            return;
        }

        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo Nombre (Ingrese 'N/A' para mantener el dato actual)", estudiante.getNombre());
        if (!nuevoNombre.equals("N/A")) {
            estudiante.setNombre(nuevoNombre);
        }

        String nuevoEmail = JOptionPane.showInputDialog(this, "Nuevo Email (Ingrese 'N/A' para mantener el dato actual)", estudiante.getEmail());
        if (!nuevoEmail.equals("N/A")) {
            estudiante.setEmail(nuevoEmail);
        }

        String nuevaFechaNacimientoStr = JOptionPane.showInputDialog(this, "Nueva Fecha de Nacimiento (dd/MM/yyyy) (Ingrese 'N/A' para mantener el dato actual)",
                new SimpleDateFormat("dd/MM/yyyy").format(estudiante.getFechaNacimiento()));

        if (nuevaFechaNacimientoStr != null && !nuevaFechaNacimientoStr.equals("N/A")) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat.setLenient(false);
                Date nuevaFechaNacimientoUtil = dateFormat.parse(nuevaFechaNacimientoStr);

                SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String nuevaFechaNacimientoSqlStr = sqlDateFormat.format(nuevaFechaNacimientoUtil);
                java.sql.Date nuevaFechaNacimientoSql = java.sql.Date.valueOf(nuevaFechaNacimientoSqlStr);

                estudiante.setFechaNacimiento(nuevaFechaNacimientoSql);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Fecha de nacimiento no válida. Utilice el formato dd/MM/yyyy.");
                return;
            }
        }


        String nuevoEstadoStr = JOptionPane.showInputDialog(this, "Nuevo Estado (0 = Inactivo, 1 = Activo) (Ingrese 'N/A' para mantener el dato actual)", estudiante.getEstado());
        if (!nuevoEstadoStr.equals("N/A")) {
            int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
            estudiante.setEstado(nuevoEstado);
        }

        estudianteController.modificarEstudiante(estudiante);
        textArea.append("Estudiante modificado exitosamente.\n");
    }

    private void eliminarEstudiante() {
        String idEstStr = JOptionPane.showInputDialog(this, "Identificación del estudiante a modificar:");
        int estudianteID = Integer.parseInt(idEstStr);

        boolean eliminado = estudianteController.eliminarEstudiante(estudianteID);
        if (eliminado) {
            textArea.append("Estudiante eliminado exitosamente.\n");
        } else {
            textArea.append("Estudiante no eliminado.\n");
        }
    }

    private void consultarEstudiante() {
        String idEstStr = JOptionPane.showInputDialog(this, "Identificación del estudiante a modificar:");
        int estudianteID = Integer.parseInt(idEstStr);

        EstudianteModel estudiante = estudianteController.consultarEstudiante(estudianteID);
        if (estudiante != null) {
            textArea.append("Estudiante encontrado con el ID: " + estudiante.getId() +
                    "\n Nombre: " + estudiante.getNombre() + " >> ID: "+estudiante.getIdentificacion()+" >> Email: "+estudiante.getEmail()+" >> Fecha Nacimiento: "+estudiante.getFechaNacimiento()+" >> Estado: "+estudiante.getEstado()+"\n");
        } else {
            textArea.append("Estudiante no encontrado.\n");
        }
    }

    private void listarEstudiante() {
        List<EstudianteModel>estudiantes = estudianteController.listarEstudiantes();
        if(!estudiantes.isEmpty()) {
            for (EstudianteModel estudiante : estudiantes) {
                textArea.append("Nombre: " + estudiante.getNombre() + " >> ID: "+estudiante.getIdentificacion()+" >> Email: "+estudiante.getEmail()+" >> Fecha Nacimiento: "+estudiante.getFechaNacimiento()+" >> Estado: "+estudiante.getEstado()+"\n");
            }
        } else {
            textArea.append("Estudiantes no encontrados.\n");
        }
    }
}
