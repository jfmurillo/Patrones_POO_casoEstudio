package controller;

import dao.CursoDAO;
import model.CursoModel;
import model.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoController {

    private ConsoleView consoleView;

    private CursoDAO cursoDAO;

     public CursoController(ConsoleView consoleView ){
         this.consoleView = consoleView;
         Connection connection = conexion.getConnection();
         this.cursoDAO = new CursoDAO(connection);
     }

    public void agregarCurso (CursoModel cursoModel){
         try {
             cursoDAO.agregarCurso(cursoModel);
             consoleView.showMessage("Datos insertados");
         }catch (SQLException e){
             consoleView.errorMessage("Datos no insertados");
             consoleView.showMessage(e.getMessage());
         }
    }

    public void modificarCurso (CursoModel cursoModel){


        try {
            cursoDAO.modificarCurso(cursoModel);
            consoleView.showMessage("Datos modificados correctamente");
        }catch (SQLException e){
            consoleView.errorMessage("Datos no han sido modificados");
            consoleView.showMessage(e.getMessage());
        }
    }

    public boolean eliminarCurso (int id){
         try{
             cursoDAO.eliminacionCurso(id);
             consoleView.showMessage("Datos eliminados correctamente");
         } catch (SQLException e){
             consoleView.errorMessage("Datos no eliminados");
             consoleView.showMessage(e.getMessage());
         }
        return false;
    }

    public CursoModel consultarCurso(int id){
         try {
             CursoModel curso = cursoDAO.consultarCurso(id);
             consoleView.showMessage("Datos del curso consultados correctamente");
             return curso;
         } catch (SQLException e){
             consoleView.errorMessage("Error al consultar los datos del curso");
             consoleView.showMessage(e.getMessage());
             return null;
         }

    }

    public List<CursoModel> listarCursos() {
        try {
            List<CursoModel> cursos = cursoDAO.listarCursos();
            consoleView.showMessage("Datos de los cursos consultados correctamente");
            return cursos;
        } catch (SQLException e) {
            consoleView.errorMessage("Error al consultar los datos de los cursos");
            consoleView.showMessage(e.getMessage());
            return new ArrayList<>();
        }
    }

}
