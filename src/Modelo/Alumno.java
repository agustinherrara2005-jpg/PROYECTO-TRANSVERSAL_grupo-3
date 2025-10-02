
package Modelo;

public class Alumno {
    
private int idAlumno;
private String nombre;
private String apellido;
private int fechaDeNacimiento;
private int dni;
private boolean estado;

    public Alumno() {}

    public Alumno(int idAlumno, String nombre, String apellido, int fechaDeNacimiento, int dni, boolean estado) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.dni = dni;
        this.estado = estado;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

@Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", fechaNacimiento=" + fechaDeNacimiento +
                ", estado=" + estado +
                '}';
    }
}
