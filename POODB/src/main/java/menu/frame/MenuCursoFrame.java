package menu.frame;

import controller.CursoController;
import model.CursoModel;
import view.ConsoleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuCursoFrame extends JFrame {
    private CursoController cursoController;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JButton btnListar;
    private JButton btnSalir;
    private JTextArea textArea;

    public MenuCursoFrame() {
        setTitle("Menu de Cursos");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1));

        // Crear botones
        btnAgregar = new JButton("Agregar nuevos cursos");
        btnModificar = new JButton("Modificación de cursos existentes");
        btnEliminar = new JButton("Eliminación de cursos");
        btnConsultar = new JButton("Consulta de cursos");
        btnListar = new JButton("Lista de cursos");
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
                agregarCurso();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarCurso();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarCurso();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarCurso();
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
        cursoController = new CursoController(consoleView);
    }

    private void agregarCurso() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del curso:");
        String desc = JOptionPane.showInputDialog(this, "Descripción del curso:");
        int estado = Integer.parseInt(JOptionPane.showInputDialog(this, "Estado (0 = Inactivo, 1 = Activo):"));

        CursoModel cursoModel = new CursoModel(nombre, desc, estado);
        cursoController.agregarCurso(cursoModel);
        textArea.append("Curso agregado exitosamente.\n");
    }

    private void modificarCurso() {
        String idCursoStr = JOptionPane.showInputDialog(this, "Id del curso a modificar:");
        int idCurso = Integer.parseInt(idCursoStr);

        CursoModel cursoModel = cursoController.consultarCurso(idCurso);
        if (cursoModel != null) {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre (ingrese 'N/A' para mantener):", cursoModel.getNombre());
            String nuevaDesc = JOptionPane.showInputDialog(this, "Nueva descripción (ingrese 'N/A' para mantener):", cursoModel.getDescripcion());
            String nuevoEstadoStr = JOptionPane.showInputDialog(this, "Nuevo Estado (0 = Inactivo, 1 = Activo, ingrese 'N/A' para mantener):", cursoModel.getEstado());

            if (nuevoNombre != null && !nuevoNombre.isEmpty()) { //modificar validaciones
                cursoModel.setNombre(nuevoNombre);
            }
            if (nuevaDesc != null && !nuevaDesc.isEmpty()) {
                cursoModel.setDescripcion(nuevaDesc);
            }
            if (nuevoEstadoStr != null && !nuevoEstadoStr.isEmpty()) {
                cursoModel.setEstado(Integer.parseInt(nuevoEstadoStr));
            }

            cursoController.modificarCurso(cursoModel);
            textArea.append("Curso modificado exitosamente.\n");
        } else {
            textArea.append("Curso no encontrado.\n");
        }
    }

    private void eliminarCurso() {
        String idCursoStr = JOptionPane.showInputDialog(this, "Id del curso a eliminar:");
        int idCurso = Integer.parseInt(idCursoStr);

        boolean eliminado = cursoController.eliminarCurso(idCurso);
        if (eliminado) {
            textArea.append("Curso eliminado exitosamente.\n");
        } else {
            textArea.append("Curso no encontrado.\n");
        }
    }

    private void consultarCurso() {
        String idCursoStr = JOptionPane.showInputDialog(this, "Id del curso a consultar:");
        int idCurso = Integer.parseInt(idCursoStr);

        CursoModel cursoModel = cursoController.consultarCurso(idCurso);
        if (cursoModel != null) {
            textArea.append("Curso encontrado: " +
                    ">> ID: " + cursoModel.getEstado() + "Nombre: " + cursoModel.getNombre() + ">> Descripcion: " + cursoModel.getDescripcion() + "\n");
        } else {
            textArea.append("Curso no encontrado.\n");
        }
    }

    private void listarCurso() {
        List<CursoModel> cursos = cursoController.listarCursos();
        if (!cursos.isEmpty()) {
            for (CursoModel curso : cursos) {
                textArea.append(">> ID: " + curso.getEstado() +"Nombre: " + curso.getNombre() + ">> Descripcion: " + curso.getDescripcion() + "\n");
            }
        } else {
            textArea.append("No hay cursos disponibles.\n");
        }
    }
}
