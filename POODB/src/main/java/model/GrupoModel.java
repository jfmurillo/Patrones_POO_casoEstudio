package model;

public class GrupoModel {

    private int id;
    private String nombre;
    private String descripcion;
    private int estado;



    public GrupoModel(String nombre,String descripcion ,int estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}



/*
DELETE FROM `curso` WHERE 0
UPDATE `curso` SET `id`='[value-1]',`nombre`='[value-2]',`estado`='[value-3]' WHERE 1
INSERT INTO `curso`(`id`, `nombre`, `estado`) VALUES ('[value-1]','[value-2]','[value-3]')
SELECT `id`, `nombre`, `estado` FROM `curso` WHERE 1

*/
