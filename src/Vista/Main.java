
package Vista;

import Modelo.Alumno;
import Persistencia.sbConexion;
import Persistencia.AlumnoData;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        Alumno estudioso = new Alumno("Cristy", fecha, false); // entidad

        new Main().conectar(estudioso);

        System.out.println("Alumno " + estudioso.getNombre() + " guardado con éxito");
    }

    void conectar(Alumno estudioso) {
        sbConexion conexion = new sbConexion("jdbc:mysql://localhost/", "root", "");
        AlumnoData alumnoData = new AlumnoData(conexion);

        alumnoData.guardarAlumno(estudioso);
        Alumno alu = alumnoData.buscarAlumno(estudioso.getIdAlumno());

        System.out.println("Datos: " + alu);
    }
}
