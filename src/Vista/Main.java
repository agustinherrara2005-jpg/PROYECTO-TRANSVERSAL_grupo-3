    package Vista;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import Persistencia.AlumnoData;
import Persistencia.MateriaData;
import Persistencia.InscripcionData;
import Persistencia.sbConexion;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List; //se usara en futuro

public class Main {
    public static void main(String[] args) throws ParseException {
        // Inicializa la conexión
        sbConexion.getConexion();
        System.out.println("Probando conexión a la DB...");

        AlumnoData ad = new AlumnoData(new sbConexion());
        MateriaData md = new MateriaData(new sbConexion());
        InscripcionData id = new InscripcionData(new sbConexion());

        // Convertir String a Date
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = formato.parse("2025-01-01");
        
        // Crear y guardar alumno
        Alumno a = new Alumno("Juan", "Perez", "12345678", fecha);
        boolean okA = ad.guardarAlumno(a);
        System.out.println("Alumno guardado? " + okA + " -> " + a);

        // Crear y guardar materia
        Materia m = new Materia("Matemáticas");
        boolean okM = md.guardarMateria(m);
        System.out.println("Materia guardada? " + okM + " -> " + m);

        // Inscribir
        Inscripcion ins = new Inscripcion(a, m );
        boolean okIns = id.guardarInscripcion(ins);
        System.out.println("Inscripción guardada? " + okIns + " -> " + ins);

        // Listar alumnos y materias
        System.out.println("Listado de alumnos:");
        ad.listarAlumnos().forEach(System.out::println);

        System.out.println("Listado de materias:");
        md.listarMaterias().forEach(System.out::println);

        System.out.println("Listado de inscripciones: EN PROGRESO \n --> REVISAR LINEA 53 DE MAIN Y CREAR LISTAR INSCRIPCION<--");
        //List<Inscripcion> insList = id.listarInscripciones();
        //insList.forEach(System.out::println);

        // Cerrar conexion al terminar
        sbConexion.cerrarConexion();
    }
}
