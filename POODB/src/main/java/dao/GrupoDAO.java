package dao;

import model.GrupoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    private Connection connection;

    public GrupoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarGrupo (GrupoModel objeto) throws SQLException {
        String query = "INSERT INTO `jmc_grupo`(`nombre`,`descripcion`,`estado`) VALUES ( ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getEstado());
            stmt.executeUpdate();
        }
    }

    public void modificarGrupo(GrupoModel objeto) throws SQLException {
        String query = "UPDATE `jmc_grupo` SET `nombre` = ?, `descripcion` = ?, `estado` = ? WHERE `id` = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setInt(3, objeto.getEstado());
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminacionGrupo(int grupoId) throws SQLException {
        String query = "DELETE FROM `jmc_grupo` WHERE `id` = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            stmt.executeUpdate();
        }
    }

    public GrupoModel consultarGrupo(int grupoId) throws SQLException {
        String query = "SELECT * FROM `jmc_grupo` WHERE `id` = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupoId);
            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    GrupoModel grupo = new GrupoModel(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("estado"));
                    grupo.setEstado(rs.getInt("id"));
                    return grupo;
                } else {
                    throw new SQLException("No se encontro el grupo: " + grupoId);
                }
            }
        }
    }

    public List<GrupoModel> listarGrupos() throws SQLException {
        List<GrupoModel> grupos = new ArrayList<GrupoModel>();
        String query = "SELECT * FROM `jmc_grupo`";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                GrupoModel grupo = new GrupoModel(rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("estado"));
                grupo.setEstado(rs.getInt("id"));
                grupos.add(grupo);
            }
        }
        return grupos;
    }
}


/*
DELETE FROM `curso` WHERE 0
UPDATE `curso` SET `id`='[value-1]',`nombre`='[value-2]',`estado`='[value-3]' WHERE 1
INSERT INTO `curso`(`id`, `nombre`, `estado`) VALUES ('[value-1]','[value-2]','[value-3]')
SELECT `id`, `nombre`, `estado` FROM `curso` WHERE 1

*/
