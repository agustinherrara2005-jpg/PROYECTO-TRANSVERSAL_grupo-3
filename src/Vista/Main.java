    package Vista;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import Persistencia.AlumnoData;
import Persistencia.MateriaData;
import Persistencia.InscripcionData;
import Persistencia.sbConexion;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Inicializa la conexión
        sbConexion.getConexion();
        System.out.println("Probando conexión a la DB...");

        AlumnoData ad = new AlumnoData(new sbConexion());
        MateriaData md = new MateriaData(new sbConexion());
        InscripcionData id = new InscripcionData(new sbConexion());

        // Crear y guardar alumno
        Alumno a = new Alumno("Juan", "Perez", "12345678");
        boolean okA = ad.guardarAlumno(a);
        System.out.println("Alumno guardado? " + okA + " -> " + a);

        // Crear y guardar materia
        Materia m = new Materia("Matemáticas");
        boolean okM = md.guardarMateria(m);
        System.out.println("Materia guardada? " + okM + " -> " + m);

        // Inscribir
        Inscripcion ins = new Inscripcion(a, m, new Date());
        boolean okIns = id.guardarInscripcion(ins);
        System.out.println("Inscripción guardada? " + okIns + " -> " + ins);

        // Listar alumnos y materias
        System.out.println("Listado de alumnos:");
        ad.listarAlumnos().forEach(System.out::println);

        System.out.println("Listado de materias:");
        md.listarMaterias().forEach(System.out::println);

        System.out.println("Listado de inscripciones:");
        List<Inscripcion> insList = id.listarInscripciones();
        insList.forEach(System.out::println);

        // Cerrar conexion al terminar
        sbConexion.cerrarConexion();
    }
}
