package Modelo;

import java.util.Date;

public class Inscripcion {
    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private Date fecha;
    private boolean estado;

    public Inscripcion() {
        this.estado = true;
    }

    public Inscripcion(int idInscripcion, Alumno alumno, Materia materia, Date fecha, boolean estado) {
        this.idInscripcion = idInscripcion;
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Inscripcion(Alumno alumno, Materia materia, Date fecha) {
        this.alumno = alumno;
        this.materia = materia;
        this.fecha = fecha;
        this.estado = true;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "idInscripcion=" + idInscripcion +
                ", alumno=" + alumno +
                ", materia=" + materia +
                ", fecha=" + fecha +
                ", estado=" + estado +
                '}';
    }
}
