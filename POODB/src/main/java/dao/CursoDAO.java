package dao;

import model.CursoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarCurso(CursoModel objeto) throws SQLException {
        String query = "INSERT INTO `jmc_curso`(`nombre`,`descripcion`,`estado`) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getEstado());
            stmt.executeUpdate();
        }
    }

    public void modificarCurso(CursoModel objeto) throws SQLException {
        String query = "UPDATE `jmc_curso` SET `nombre` = ?, `descripcion` = ?, `estado` = ? WHERE `id` = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getEstado());
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminacionCurso(int cursoId) throws SQLException {
        String query = "DELETE FROM `jmc_curso` WHERE `id` = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cursoId);
            stmt.executeUpdate();
        }
    }

    public CursoModel consultarCurso(int cursoId) throws SQLException {
        String query = "SELECT * FROM `jmc_curso` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cursoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CursoModel curso = new CursoModel(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("estado"));
                    curso.setId(rs.getInt("id"));
                    return curso;
                } else {
                    throw new SQLException("Curso no encontrado con ID: " + cursoId);
                }
            }
        }
    }


    public List<CursoModel> listarCursos() throws SQLException {
        List<CursoModel> cursos = new ArrayList<>();
        String query = "SELECT * FROM jmc_curso";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CursoModel curso = new CursoModel(rs.getString("nombre"),rs.getString("descripcion") ,rs.getInt("estado"));
                curso.setId(rs.getInt("id"));
                cursos.add(curso);
            }
        }
        return cursos;
    }
}
/*
DELETE FROM `curso` WHERE 0
UPDATE `curso` SET `id`='[value-1]',`nombre`='[value-2]',`estado`='[value-3]' WHERE 1
INSERT INTO `curso`(`id`, `nombre`, `estado`) VALUES ('[value-1]','[value-2]','[value-3]')
SELECT `id`, `nombre`, `estado` FROM `curso` WHERE 1

*/
