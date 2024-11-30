package org.example.view;

import jdk.jshell.execution.Util;
import org.example.model.Alumno;
import org.example.model.Materia;
import org.example.model.MateriaEnum;
import org.example.service.AlumnoService;
import org.example.service.ArchivoService;
import org.example.service.PromedioServicioImp;
import org.example.util.Utilidad;

import java.sql.SQLOutput;
import java.util.*;

public class Menu extends MenuTemplate {
    AlumnoService alumnoService = new AlumnoService();
    ArchivoService archivoService = new ArchivoService(new PromedioServicioImp());
    static {


        try {
            //creacion alumno prueba
            Alumno alumno = new Alumno();
            alumno.setRut("195981027");
            alumno.setNombre("Claudio");
            alumno.setApellido("Cabrera");
            AlumnoService alumnoService = new AlumnoService();
            alumnoService.crearAlumno(alumno);
            Materia materia = new Materia();
            materia.setNombre(MateriaEnum.MATEMATICAS);
            materia.addNota(5.0f);
            materia.addNota(6.0f);
            materia.addNota(4.2f);
            alumno.addMateria(materia);
            Materia materia2 = new Materia();
            materia2.setNombre(MateriaEnum.LENGUAJE);
            materia2.addNota(5.1f);
            materia2.addNota(6.7f);
            materia2.addNota(4.2f);
            alumno.addMateria(materia2);

            Alumno alumno2 = new Alumno();
            alumno2.setRut("1233333");
            alumno2.setNombre("assdo");
            alumno2.setApellido("gfdfgd");
            alumnoService.crearAlumno(alumno2);
            Materia materia3 = new Materia();
            materia3.setNombre(MateriaEnum.MATEMATICAS);
            materia3.addNota(5.7f);
            materia3.addNota(6.5f);
            materia3.addNota(4.8f);
            alumno2.addMateria(materia3);
            Materia materia4 = new Materia();
            materia4.setNombre(MateriaEnum.LENGUAJE);
            materia4.addNota(5.4f);
            materia4.addNota(6.2f);
            materia4.addNota(4.1f);
            alumno2.addMateria(materia4);



        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
    @Override
    public void exportarDatos() throws Exception {

        Utilidad.limpiarPantalla();
        archivoService.exportarDatos(alumnoService.getListaAlumnos(), "exports/promedios.txt");
        Utilidad.mensajeColor("--- DATOS EXPORTADOS CORRECTAMENTE.", Utilidad.VERDE);


    }

    @Override
    public void crearAlummno() throws Exception {

        Utilidad.mensajeColor("--- CREAR ALUMNO ---", Utilidad.AMARILLO);
        System.out.print("Ingresa RUT:");
        String rut = Utilidad.sc().nextLine();

        System.out.print("Ingresa Nombre:");
        String nombre = Utilidad.sc().nextLine();

        System.out.print("Ingresa Apellido:");
        String apellido = Utilidad.sc().nextLine();

        if (rut.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            throw new Exception("Los campos no pueden estar vacios");
        }

        Alumno alumno = new Alumno();
        alumno.setRut(rut);
        alumno.setNombre(nombre);
        alumno.setApellido(apellido);

        alumnoService.crearAlumno(alumno);
        Utilidad.limpiarPantalla();
        Utilidad.mensajeColor("--- ALUMNO CREADO! ---\n", Utilidad.VERDE);


    }

    @Override
    public void agregarMateria() throws Exception {

        Utilidad.limpiarPantalla();

        Utilidad.mensajeColor("--- AGREGAR MATERIA ---", Utilidad.AMARILLO);
        if (alumnoService.cantidadAlumnos() == 0) {
            throw new Exception("No hay alumnos registrados");
        }
        System.out.print("Ingresa RUT:");
        String rut = Utilidad.sc().nextLine();
        if (rut.isEmpty()) {
            throw new Exception("El rut no puede estar vacio");
        }
        if (!alumnoService.existeAlumno(rut)) {
            throw new Exception("El alumno no existe");
        }


        List<MateriaEnum> listaMaterias = new ArrayList<>();
        int index = 0;
        for (MateriaEnum materiaEnum : MateriaEnum.values()) {
            System.out.println(index + ". " + materiaEnum);
            listaMaterias.add(index++, materiaEnum);

        }

        Utilidad.mensajeColor("Seleccione la materia:", Utilidad.CIAN);
        if (Utilidad.sc().hasNextInt()) {
            int seleccion = Utilidad.sc().nextInt();
            if (seleccion >= 0 && seleccion < index) {
                MateriaEnum materiaEnum = listaMaterias.get(seleccion);
                Materia materia = new Materia();
                materia.setNombre(materiaEnum);
                alumnoService.agregarMateria(rut, materia);
                Utilidad.limpiarPantalla();
                Utilidad.mensajeColor("--- MATERIA AGREGADA! ---", Utilidad.VERDE);
            }else{
                throw new InputMismatchException("Valor ingresado de materia inválido.");
            }
        } else {
            throw new InputMismatchException("Valor ingresado de materia inválido.");
        }
    }


    @Override
    public void agregarNotaPasoUno() throws Exception {

        Utilidad.limpiarPantalla();
        Utilidad.mensajeColor("--- AGREGAR NOTA ---", Utilidad.AMARILLO);
        if (alumnoService.cantidadAlumnos() == 0) {
            throw new Exception("No hay alumnos registrados");
        }
        System.out.print("Ingresa RUT:");
        String rut = Utilidad.sc().nextLine();
        if (rut.isEmpty()) {
            throw new Exception("El rut no puede estar vacio");
        }
        if (!alumnoService.existeAlumno(rut)) {
            throw new Exception("El alumno no existe");
        }

        List<Materia> listaMaterias = alumnoService.materiasPorAlumno(rut);
        if(listaMaterias.size() == 0) {
            throw new Exception("El alumno no tiene materias");
        }
        Utilidad.mensajeColor("Alumno tiene las siguientes materias agregadas", Utilidad.MAGENTA);

        for (int i = 0; i < listaMaterias.size(); i++) {
            System.out.println(i + ". " + listaMaterias.get(i).getNombre());
        }
        Utilidad.mensajeColor("Seleccione la materia:", Utilidad.CIAN);

        if (Utilidad.sc().hasNextInt()) {
            int seleccion = Utilidad.sc().nextInt();
            if (seleccion >= 0 && seleccion < listaMaterias.size()) {
                Materia materia = listaMaterias.get(seleccion);
                Alumno alumno = alumnoService.obtieneAlumno(rut);
                Materia matAlumno = alumno.getMaterias().stream().filter(m -> m.getNombre().equals(materia.getNombre())).findFirst().orElse(null);
                if (matAlumno != null) {
                    Utilidad.mensajeColor("Ingresar nota:", Utilidad.CIAN, true);
                    if (Utilidad.sc().hasNextFloat()) {
                        float nota = Utilidad.sc().nextFloat();
                        matAlumno.addNota(nota);
                        Utilidad.limpiarPantalla();
                        Utilidad.mensajeColor("Nota agregada!", Utilidad.VERDE);
                    } else {
                        throw new InputMismatchException("Valor ingresado de nota inválido.");
                    }
                } else {
                    throw new Exception("No se encontró materia de alumno");
                }
            }
        } else {
            throw new InputMismatchException("Valor ingresado de materia inválido.");
        }

    }

    @Override
    public void listarAlummnos() throws Exception {

        Utilidad.limpiarPantalla();
        Utilidad.mensajeColor("--- LISTA DE ALUMNOS ---", Utilidad.AMARILLO);
        Map<String, Alumno> listaAlumnos = alumnoService.listarAlumnos();
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            throw new Exception("No hay alumnos registrados");
        } else {
            for (Map.Entry<String, Alumno> alumno : listaAlumnos.entrySet()) {
                System.out.println(alumno.getValue().toString());
            }

        }
    }


    @Override
    public void terminarPrograma() {
        Utilidad.limpiarPantalla();
        this.salir = true;
        Utilidad.mensajeColor("SALIENDO...", Utilidad.MAGENTA);

    }


}
