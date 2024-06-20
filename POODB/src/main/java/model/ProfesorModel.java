package model;

public class ProfesorModel {
    private int id;
    private String nombre;
    private String identificacion;
    private String email;
    private String departamento;
    private int estado;

    public ProfesorModel(String nombre, String identificacion, String email, String departamento, int estado) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.departamento = departamento;
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}