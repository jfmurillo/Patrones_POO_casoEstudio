package menu.frame;

import controller.CursoController;
import controller.GrupoController;
import model.CursoModel;
import model.GrupoModel;
import view.ConsoleView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuGrupoFrame extends JFrame {
    private GrupoController grupoController;
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnConsultar;
    private JButton btnListar;
    private JButton btnSalir;
    private JTextArea textArea;

    public MenuGrupoFrame() {
        setTitle("Menu de Grupos");
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
        btnListar = new JButton("Lista de grupos");
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
                agregarGrupo();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarGrupo();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarGrupo();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarGrupo();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarGrupo();
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
        grupoController = new GrupoController(consoleView);
    }

    private void agregarGrupo() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre: ");
        String desc = JOptionPane.showInputDialog(this, "Descripción: ");
        int estado = Integer.parseInt(JOptionPane.showInputDialog(this, "Estado (0 = Inactivo, 1 = Activo): "));

        GrupoModel grupo = new GrupoModel(nombre, desc, estado);
        grupoController.agregarGrupo(grupo);
        textArea.append("Grupo agregado exitosamente.\n");
    }

    private void modificarGrupo() {
        String idGrupo = JOptionPane.showInputDialog(this, "ID del grupo a modificar:");
        int IDGrupo = Integer.parseInt(idGrupo);

        GrupoModel grupo = grupoController.consultarGrupo(IDGrupo);
        if (grupo != null) {
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre (ingrese 'N/A' para mantener):", grupo.getNombre());
            String nuevaDesc = JOptionPane.showInputDialog(this, "Nueva descripción (ingrese 'N/A' para mantener):", grupo.getDescripcion());
            String nuevoEstadoStr = JOptionPane.showInputDialog(this, "Nuevo Estado (0 = Inactivo, 1 = Activo, ingrese 'N/A' para mantener):", grupo.getEstado());

            if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                grupo.setNombre(nuevoNombre);
            }
            if (nuevaDesc != null && !nuevaDesc.isEmpty()) {
                grupo.setDescripcion(nuevaDesc);
            }
            if (nuevoEstadoStr != null && !nuevoEstadoStr.isEmpty()) {
                grupo.setEstado(Integer.parseInt(nuevoEstadoStr));
            }

            grupoController.modificarGrupo(grupo);
            textArea.append("Grupo modificado exitosamente.\n");
        } else {
            textArea.append("Grupo no encontrado.\n");
        }
    }

    private void eliminarGrupo() {
        String idGrupoStr = JOptionPane.showInputDialog(this, "ID del grupo a eliminar:");
        int idGrupo = Integer.parseInt(idGrupoStr);

        boolean eliminado = grupoController.eliminarGrupo(idGrupo);
        if (eliminado) {
            textArea.append("Grupo eliminado exitosamente.\n");
        } else {
            textArea.append("Grupo no encontrado.\n");
        }
    }

    private void consultarGrupo() {
        String idGrupoStr = JOptionPane.showInputDialog(this, "ID del grupo a consultar:");
        int idGrupo = Integer.parseInt(idGrupoStr);

        GrupoModel grupo = grupoController.consultarGrupo(idGrupo);
        if (grupo != null) {
            textArea.append("Curso encontrado: " +
                    "ID" + grupo.getId()+ "Nombre: " + grupo.getNombre() + ", Descripcion: " + grupo.getDescripcion() + ", Estado: " + grupo.getEstado() + "\n");
        } else {
            textArea.append("Grupo no encontrado.\n");
        }
    }

    private void listarGrupo() {
        List<GrupoModel> grupos = grupoController.listarGrupos();
        if (!grupos.isEmpty()) {
            for (GrupoModel grupo : grupos) {
                textArea.append("ID "+grupo.getEstado()+ "// Nombre: " + grupo.getNombre() + "// Descripcion: " + grupo.getDescripcion() + "\n");
            }
        } else {
            textArea.append("No hay grupos disponibles.\n");
        }
    }
}
