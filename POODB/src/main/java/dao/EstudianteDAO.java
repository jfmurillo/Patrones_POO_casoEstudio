package dao;

import model.EstudianteModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class EstudianteDAO {
    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarEstudiante(EstudianteModel estudiante) throws SQLException{
        String query = "INSERT INTO `jmc_estudiante`(`nombre`,`identificacion`,`email`,`fechaNacimiento`,`estado`) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getIdentificacion());
            ps.setString(3,estudiante.getEmail());
            ps.setDate(4, estudiante.getFechaNacimiento());
            ps.setInt(5, estudiante.getEstado());
            ps.executeUpdate();
        }
    }

    public void modificarEstudiante(EstudianteModel estudiante) throws SQLException {
        String query = "UPDATE `jmc_estudiante` SET `nombre` = ?,`identificacion` = ? ,`email` = ?, `fechaNacimiento` = ?, `estado` = ? WHERE `id` = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, estudiante.getNombre());
            ps.setString(2, estudiante.getIdentificacion());
            ps.setString(3, estudiante.getEmail());
            ps.setDate(4, estudiante.getFechaNacimiento());
            ps.setInt(5, estudiante.getEstado());
            ps.executeUpdate();
        }
    }

    public void eliminarEstudiante(int estudianteID) throws SQLException {
        String query = "DELETE FROM `jmc_estudiante` WHERE `id` = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, estudianteID);
            ps.executeUpdate();
        }
    }

    public EstudianteModel consultarEstudiante(int estudianteID) throws SQLException {
        String query = "SELECT * FROM `jmc_estudiante` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, estudianteID);
            try (ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    EstudianteModel estudiante = new EstudianteModel(rs.getString("nombre"), rs.getString("identificacion"), rs.getString("email"), rs.getDate("fechaNacimiento"), rs.getInt("estado"));
                    estudiante.setId(rs.getInt("id"));
                    return estudiante;
                } else {
                    throw new SQLException("Estudiante no encontrado con ID: " + estudianteID);
                }
            }
        }
    }

    public List<EstudianteModel> listarEstudiante() throws SQLException {
        List<EstudianteModel> estudianteModels = new ArrayList<>();
        String query = "SELECT * FROM `jmc_estudiante`";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EstudianteModel estudiante = new EstudianteModel(rs.getString("nombre"),rs.getString("identificacion"),rs.getString("email"),rs.getDate("fechaNacimiento"),rs.getInt("estado"));
                estudiante.setId(rs.getInt("id"));
                estudianteModels.add(estudiante);
            }
        }
        return estudianteModels;
    }

}

