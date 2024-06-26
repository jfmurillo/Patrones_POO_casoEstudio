package menu;

import controller.EstudianteController;
import model.EstudianteModel;
import view.ConsoleView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;



public class MenuEstudiante {
    private Scanner scanner;
    private boolean exit;
    Menu menu;

    public MenuEstudiante() {
        this.scanner = new Scanner(System.in);
        this.exit = false;
    }

    public void showMenuEstudiante() throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        EstudianteController estudianteController = new EstudianteController(consoleView);

        while (!exit) {
            System.out.println("====Menu de Estudiantes====");
            System.out.println("1. Inserción de nuevos registros.");
            System.out.println("2. Modificación de registros existentes.");
            System.out.println("3. Eliminación de registros.");
            System.out.println("4. Consulta de registros.");
            System.out.println("5. Lista de registros.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    insertarEstudiante(estudianteController);
                    break;
                case 2:
                    modificarEstudiante(estudianteController);
                    break;
                case 3:
                    eliminarEstudiante(estudianteController);
                    break;
                case 4:
                    consultarEstudiantes(estudianteController);
                    break;
                case 5:
                    listarEstudiantes(estudianteController);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    menu.showMenu();
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    private void insertarEstudiante(EstudianteController estudianteController) throws SQLException {

        System.out.println("====Inserción de Nuevo Estudiante====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Fecha de Nacimiento (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();

        Date fechaNacimiento = null;
        try {
            fechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Fecha inválida, por favor usa el formato dd/MM/yyyy.");
            return;
        }
        java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());


        System.out.print("Estado (0 = Inactivo, 1 = Activo): ");
        int estado = scanner.nextInt();
        scanner.nextLine();

        EstudianteModel estudiante = new EstudianteModel(nombre, identificacion, email, sqlFechaNacimiento, estado);
        estudianteController.agregarEstudiante(estudiante);
        System.out.println("Estudiante insertado exitosamente.");
    }

    private void modificarEstudiante(EstudianteController estudianteController) {
        System.out.println("====Modificación de Estudiante====");
        System.out.print("Id del estudiante a modificar: ");
        String id = scanner.nextLine();


        EstudianteModel estudiante;
        estudiante = estudianteController.consultarEstudiante(Integer.parseInt(id));
        estudiante.setId(estudiante.getId());

        if (estudiante != null) {
            System.out.println("Estudiante encontrado: " + estudiante);
            System.out.print("Nuevo Nombre (dejar en blanco para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                estudiante.setNombre(nuevoNombre);
            }

            estudiante.setIdentificacion(estudiante.getIdentificacion());

            System.out.print("Nuevo Email (dejar en blanco para mantener): ");
            String nuevoEmail = scanner.nextLine();
            if (!nuevoEmail.isEmpty()) {
                estudiante.setEmail(nuevoEmail);
            }
            System.out.print("Nueva Fecha de Nacimiento (dd/MM/yyyy, dejar en blanco para mantener): ");
            String nuevaFechaStr = scanner.nextLine();
            if (!nuevaFechaStr.isEmpty()) {
                try {
                    Date nuevaFechaNacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(nuevaFechaStr);
                    estudiante.setFechaNacimiento(nuevaFechaNacimiento);
                } catch (ParseException e) {
                    System.out.println("Fecha inválida, por favor usa el formato dd/MM/yyyy.");
                }
            }
            System.out.print("Nuevo Estado (0 = Inactivo, 1 = Activo, dejar en blanco para mantener): ");
            String nuevoEstadoStr = scanner.nextLine();
            if (!nuevoEstadoStr.isEmpty()) {
                int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
                estudiante.setEstado(nuevoEstado);
            }

            estudianteController.modificarEstudiante(estudiante);
            System.out.println("Estudiante modificado exitosamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }

    }

    private void eliminarEstudiante(EstudianteController estudianteController) {
        System.out.println("====Eliminación de Estudiante====");
        System.out.print("Identificación del estudiante a eliminar: ");
        String identificacion = scanner.nextLine();

        boolean eliminado = estudianteController.eliminarEstudiante(Integer.parseInt(identificacion));
        if (eliminado) {
            System.out.println("Estudiante eliminado exitosamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    public void consultarEstudiantes(EstudianteController estudianteController){
        System.out.println("====Consulta de Estudiantes====");
        System.out.println("ID del estudiante");
        int idEst = scanner.nextInt();
        scanner.nextLine();

        EstudianteModel estudiante = estudianteController.consultarEstudiante(idEst);
        if (estudiante != null) {
            System.out.println("Estudiante consultado exitosamente.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    private void listarEstudiantes(EstudianteController estudianteController) {
        System.out.println("====Lista de Estudiantes====");
        List<EstudianteModel> estudiantes = estudianteController.listarEstudiantes();
        if (estudiantes != null) {
            for (EstudianteModel estudiante : estudiantes) {
                System.out.println("Estudiante: " + estudiante);
            }
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}
