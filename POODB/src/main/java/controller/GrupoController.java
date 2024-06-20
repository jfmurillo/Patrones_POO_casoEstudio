package controller;
import dao.GrupoDAO;
import model.*;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GrupoController {

    private ConsoleView consoleView;

    private GrupoDAO grupoDAO;

    public GrupoController(ConsoleView consoleView ){
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.grupoDAO = new GrupoDAO(connection);
    }

    public void agregarGrupo(GrupoModel grupoModel){
        try {
            grupoDAO.agregarGrupo(grupoModel);
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.errorMessage("Datos no insertados");
            consoleView.showMessage(e.getMessage());
        }
    }

    public void modificarGrupo(GrupoModel grupoModel){
        try {
            grupoDAO.modificarGrupo(grupoModel);
            consoleView.showMessage("Datos modificados");
        } catch (SQLException e){
            consoleView.errorMessage("Datos no modificados");
            consoleView.showMessage(e.getMessage());
        }
    }

    public boolean eliminarGrupo(int idGrupo){
        try {
            grupoDAO.eliminacionGrupo(idGrupo);
            consoleView.showMessage("Datos eliminados");
        } catch (SQLException e){
            consoleView.errorMessage("Datos no eliminados");
            consoleView.showMessage(e.getMessage());
        }
        return false;
    }

    public GrupoModel consultarGrupo(int idGrupo){
        try {
            GrupoModel grupo = grupoDAO.consultarGrupo(idGrupo);
            consoleView.showMessage("Datos consultados");
            return grupo;
        } catch (SQLException e){
            consoleView.errorMessage("Datos no consultados");
            consoleView.showMessage(e.getMessage());
            return null;
        }
    }

    public List<GrupoModel> listarGrupos(){
        try {
            List<GrupoModel> grupos = (List<GrupoModel>) grupoDAO.listarGrupos();
            consoleView.showMessage("Datos consultados");
            return grupos;
        } catch (SQLException e){
            consoleView.errorMessage("Datos no consultados");
            consoleView.showMessage(e.getMessage());
            return null;
        }
    }
}