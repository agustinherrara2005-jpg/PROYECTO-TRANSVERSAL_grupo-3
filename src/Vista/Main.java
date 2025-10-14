/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.alumno;
import persistencia.alumnoData;
import modelo.conexion;

import java.time.LocalDate;
import java.util.List;

public class main {
    public static void main(String[] args) {
        alumnoData ad = new alumnoData();

        try {
           
           alumno a1 = new alumno("Juan", "Perez", 12345678, LocalDate.of(2000, 5, 10), true);
            int idInsertado = ad.insertar(a1);
            System.out.println("Alumno insertado con ID: " + idInsertado);

            System.out.println("\n-- Lista de alumnos --");
            List<alumno> lista = ad.obtenerTodos();
            for (alumno a : lista) {
                System.out.println(a);
            }

            if (idInsertado > 0) {
                a1.setApellido("Gonzalez");
                a1.setDni(87654321);
                boolean okUpd = ad.actualizar(a1);
                System.out.println("\nActualización exitosa? " + okUpd);
            }

            if (idInsertado > 0) {
                boolean okBaja = ad.bajaLogica(idInsertado);
                System.out.println("Baja lógica exitosa? " + okBaja);
            }

            System.out.println("\n-- Lista final --");
            lista = ad.obtenerTodos();
            for (alumno a : lista) {
                System.out.println(a);
            }

        } catch (Exception e) {
            System.err.println("️ Error en Main: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
    }
}
