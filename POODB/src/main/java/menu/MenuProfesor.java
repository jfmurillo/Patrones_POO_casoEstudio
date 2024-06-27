package menu;

import controller.EstudianteController;
import controller.ProfesorController;
import dao.ProfesorDAO;
import model.EstudianteModel;
import model.ProfesorModel;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;



public class MenuProfesor {
    private Scanner scanner;
    private boolean exit;
    ProfesorDAO profesorDAO;
    Menu menu;

    public MenuProfesor() {
        this.scanner = new Scanner(System.in);
        this.exit = false;
        this.menu = new Menu();
    }

    public void showMenuProfesor() throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        ProfesorController profesorController = new ProfesorController(consoleView);

        while (!exit) {
            System.out.println("====Menu de Profesores====");
            System.out.println("1. Inserción de nuevos profesores.");
            System.out.println("2. Modificación de profesores existentes.");
            System.out.println("3. Eliminación de profesores.");
            System.out.println("4. Consulta de profesores.");
            System.out.println("5. Lista de profesores.");
            System.out.println("0. Salir al menu de inicio.");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    agregarProfesor(profesorController);
                    break;
                case 2:
                    modificarProfesor(profesorController);
                    break;
                case 3:
                    eliminarProfesor(profesorController);
                    break;
                case 4:
                    consultarProfesor(profesorController);
                    break;
                case 5:
                    listarProfesor(profesorController);
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

    private void agregarProfesor(ProfesorController profesorController) throws SQLException {

        System.out.println("====Inserción de Nuevo Profesor====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Estado (0 = Inactivo, 1 = Activo): ");
        int estado = scanner.nextInt();
        scanner.nextLine();

        ProfesorModel profesorModel = new ProfesorModel(nombre, identificacion, email, departamento, estado);
        profesorController.agregarProfesor(profesorModel);
        System.out.println("Profesor insertado exitosamente.");
    }

    private void modificarProfesor(ProfesorController profesorController) {
        System.out.println("====Modificación de Profesor====");
        System.out.print("Id del Profesor a modificar: ");
        String id = scanner.nextLine();

        ProfesorModel profesorModel = profesorController.consultarProfesor(Integer.parseInt(id));
        profesorModel.setIdentificacion(profesorModel.getIdentificacion());


        if (profesorModel != null) {
            System.out.println("Profesor encontrado: " + profesorModel);
            System.out.print("Nuevo nombre (dejar en blanco para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                profesorModel.setNombre(nuevoNombre);
            }
            profesorModel.setIdentificacion(profesorModel.getIdentificacion());
            System.out.print("Nuevo Email (dejar en blanco para mantener): ");
            String nuevoEmail = scanner.nextLine();
            if (!nuevoEmail.isEmpty()) {
                profesorModel.setEmail(nuevoEmail);
            }
            System.out.print("Nuevo departamento, dejar en blanco para mantener): ");
            String nuevodpto = scanner.nextLine();
            if (!nuevodpto.isEmpty()) {
                profesorModel.setDepartamento(nuevodpto);
            }

            System.out.print("Nuevo Estado (0 = Inactivo, 1 = Activo, dejar en blanco para mantener): ");
            String nuevoEstadoStr = scanner.nextLine();
            if (!nuevoEstadoStr.isEmpty()) {
                int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
                profesorModel.setEstado(nuevoEstado);
            }

            profesorController.modificarProfesor(profesorModel);
            System.out.println("Profesor modificado exitosamente.");
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    private void eliminarProfesor(ProfesorController profesorController) {
        System.out.println("====Eliminación de Profesor====");
        System.out.print("Identificación del Profesor a eliminar: ");
        String idProfesor = scanner.nextLine();

        boolean eliminado;
        eliminado = profesorController.eliminarProfesor(Integer.parseInt(idProfesor));
        if (eliminado) {
            System.out.println("Profesor eliminado exitosamente.");
        } else {
            System.out.println("Profesor no encontrado.");
        }
    }

    public void consultarProfesor(ProfesorController profesorController){
        System.out.println("====Consulta de Profesores====");
        System.out.println("ID del profesor");
        int idProf = scanner.nextInt();
        scanner.nextLine();

        ProfesorModel profesor = profesorController.consultarProfesor(idProf);
        if (profesor != null) {
            System.out.println("profesor consultado exitosamente " + profesor.getNombre());
        } else {
            System.out.println("profesor no encontrado.");
        }
    }

    private void listarProfesor(ProfesorController profesorController) {
        System.out.println("====Consulta de Profesores====");
        List<ProfesorModel> profesores = profesorController.listarProfesores();
        if (profesores != null) {
            for (ProfesorModel profesor : profesores) {
                System.out.println(profesor.getNombre());
            }
        } else {
            System.out.println("profesor no encontrado.");
        }
    }
}
