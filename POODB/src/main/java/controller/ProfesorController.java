package controller;

import dao.ProfesorDAO;
import model.ProfesorModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfesorController {
    private ConsoleView consoleView;
    private ProfesorDAO profesorDAO;

    public ProfesorController(ConsoleView consoleView) {
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.profesorDAO = new ProfesorDAO(connection);
    }

    public void agregarProfesor(ProfesorModel profesor) {
        try {
            profesorDAO.agregarProfesor(profesor);
            consoleView.showMessage("Profesor agregado correctamente");
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no insertados");
            consoleView.showMessage(ex.getMessage());
        }
    }

    public void modificarProfesor(ProfesorModel profesor) {
        try{
            profesorDAO.actualizarProfesor(profesor);
            consoleView.showMessage("Profesor actualizado correctamente");
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no modificados");
            consoleView.showMessage(ex.getMessage());
        }
    }

    public boolean eliminarProfesor(int id) {
        try{
            profesorDAO.eliminarProfesor(id);
            consoleView.showMessage("Profesor eliminado correctamente");
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no eliminados");
            consoleView.showMessage(ex.getMessage());
        }
        return false;
    }

    public ProfesorModel consultarProfesor(int id) {
        try {
            ProfesorModel profesores = profesorDAO.consultarProfesor(id);
            consoleView.showMessage("Profesores consultados correctamente");
            return profesores;
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no insertados");
            consoleView.showMessage(ex.getMessage());
            return null;
        }
    }

    public List<ProfesorModel> listarProfesores() {
        try {
            List<ProfesorModel>profesores = profesorDAO.listarProfesores();
            consoleView.showMessage("Profesores consultados correctamente");
            return profesores;
        } catch (SQLException ex) {
            consoleView.showMessage("Datos no insertados");
            consoleView.showMessage(ex.getMessage());
            return null;
        }
    }
}