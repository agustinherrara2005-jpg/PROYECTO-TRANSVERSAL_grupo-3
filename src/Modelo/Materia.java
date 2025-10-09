package Modelo;

public class Materia {
    private int idMateria;
    private String nombre;
    private boolean estado;

    public Materia() {
        this.estado = true;
    }

    public Materia(int idMateria, String nombre, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Materia(String nombre) {
        this.nombre = nombre;
        this.estado = true;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "idMateria=" + idMateria +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }
}
