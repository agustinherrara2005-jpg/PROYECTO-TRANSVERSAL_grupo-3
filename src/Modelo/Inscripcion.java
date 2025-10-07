/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author kamil
 */
public class Inscripcion {
    private int idInscripcion;
    private Alumno alumno;
    private Materia materia;
    private int anioLectivo;
    private int cuatrimestre;
    private double nota;

    public Inscripcion(Alumno alumno, Materia materia, int anioLectivo, int cuatrimestre, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.anioLectivo = anioLectivo;
        this.cuatrimestre = cuatrimestre;
        this.nota = nota;
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

    public int getAnioLectivo() {
        return anioLectivo;
    }

    public void setAnioLectivo(int anioLectivo) {
        this.anioLectivo = anioLectivo;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(int cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
