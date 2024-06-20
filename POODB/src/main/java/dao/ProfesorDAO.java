package dao;

import model.ProfesorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAO {
    private Connection con;

    public ProfesorDAO(Connection con) {
        this.con = con;
    }

    public void agregarProfesor(ProfesorModel profesorModel) throws SQLException {
        String query = "INSERT INTO `jmc_profesor`(`nombre`,`identificacion`,`email`,`departamento`,`estado`) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, profesorModel.getNombre());
            ps.setString(2, profesorModel.getIdentificacion());
            ps.setString(3, profesorModel.getEmail());
            ps.setString(4, profesorModel.getDepartamento());
            ps.setInt(5, profesorModel.getEstado());
            ps.executeUpdate();
        }
    }

    public void actualizarProfesor(ProfesorModel profesorModel) throws SQLException {
        String query = "UPDATE `jmc_profesor` SET `nombre` = ?,`identificacion` = ? ,`email` = ?, `departamento` = ?, `estado` = ? WHERE `id` = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, profesorModel.getNombre());
            ps.setString(2, profesorModel.getIdentificacion());
            ps.setString(3, profesorModel.getEmail());
            ps.setString(4, profesorModel.getDepartamento());
            ps.setInt(5, profesorModel.getEstado());
            ps.executeUpdate();
        }
    }

    public void eliminarProfesor(int id) throws SQLException {
        String query = "DELETE FROM `jmc_profesor` WHERE `id` = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public ProfesorModel consultarProfesor(int id) throws SQLException {
        String query = "SELECT * FROM `jmc_profesor` WHERE `id` = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    ProfesorModel profesor = new ProfesorModel(rs.getString("nombre"),rs.getString("identificacion"),rs.getString("email"),rs.getString("departamento"),rs.getInt("estado"));
                    profesor.setId(id);
                    return profesor;
                } else {
                    throw new SQLException("No se encontro el profesor con id: " + id);
                }
            }
        }
    }

    public List<ProfesorModel> listarProfesores() throws SQLException {
        List<ProfesorModel> profesores = new ArrayList<>();
        String query = "SELECT * FROM `jmc_profesor`";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ProfesorModel profesor = new ProfesorModel(rs.getString("nombre"),rs.getString("identificacion"),rs.getString("email"),rs.getString("departamento"),rs.getInt("estado"));
                profesor.setId(rs.getInt("id"));
                profesores.add(profesor);
            }
        }
        return profesores;
    }
}