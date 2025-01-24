package controller;

import dao.EstudianteDAO;
import model.EstudianteModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteController {
    private EstudianteDAO estudianteDAO;
    private ConsoleView consoleView;

    public EstudianteController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.estudianteDAO = new EstudianteDAO(connection);
    }

    public void agregarEstudiante(EstudianteModel estudianteModel) {
        try {
            estudianteDAO.agregarEstudiante(estudianteModel);
            consoleView.showMessage("Datos agregados correctamente");
        } catch (SQLException ex) {
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(ex.getMessage());
        }
    }

    public void modificarEstudiante(EstudianteModel estudianteModel) {
        try {
            estudianteDAO.modificarEstudiante(estudianteModel);
            consoleView.showMessage("Datos modificados correctamente");
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no han sido modificados");
            consoleView.errorMessage(ex.getMessage());
        }
    }

    public boolean eliminarEstudiante(int id) {
        try {
            estudianteDAO.eliminarEstudiante(id);
            consoleView.showMessage("Estudiante eliminado correctamente");
        } catch (SQLException ex) {
            consoleView.errorMessage("Estudiante no eliminado");
            consoleView.errorMessage(ex.getMessage());
        }
        return false;
    }

    public EstudianteModel consultarEstudiante(int id) {
        try{
            EstudianteModel estudianteModel = estudianteDAO.consultarEstudiante(id);
            consoleView.showMessage("Estudiante consultado correctamente");
            return estudianteModel;
        } catch (SQLException ex) {
            consoleView.errorMessage("Error al consultar los datos del Estudiante");
            consoleView.errorMessage(ex.getMessage());
            return null;
        }

    }

    public List<EstudianteModel> listarEstudiantes() {
        try {
            List<EstudianteModel> estudiantes = (List<EstudianteModel>) estudianteDAO.listarEstudiante();
            consoleView.showMessage("Estudiantes encontrados");
            return estudiantes;
        } catch (SQLException ex) {
            consoleView.errorMessage("Error al consultar los datos de los Estudiantes");
            consoleView.errorMessage(ex.getMessage());
            return new ArrayList<>();
        }
    }
}