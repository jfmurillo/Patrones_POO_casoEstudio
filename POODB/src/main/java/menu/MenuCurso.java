package menu;

import controller.CursoController;
import dao.CursoDAO;
import model.CursoModel;
import model.GrupoModel;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuCurso {
    private Scanner scanner;
    private boolean exit;
    Menu menu;

    public MenuCurso() {
        scanner = new Scanner(System.in);
        exit = false;
        this.menu = new Menu();
    }

    public void showMenuCurso() throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        CursoController cursoController = new CursoController(consoleView);

        while (!exit) {
            System.out.println("====Menu de Cursos====");
            System.out.println("1. Inserción de nuevos cursos.");
            System.out.println("2. Modificación de cursos existentes.");
            System.out.println("3. Eliminación de cursos.");
            System.out.println("4. Consulta de cursos.");
            System.out.println("5. Lista de cursos.");
            System.out.println("0. Salir al menu de inicio.");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    agregarCurso(cursoController);
                    break;
                case 2:
                    modificarCurso(cursoController);
                    break;
                case 3:
                    eliminmarCurso(cursoController);
                    break;
                case 4:
                    consultarCurso(cursoController);
                    break;
                case 5:
                    listarCurso(cursoController);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    this.menu.showMenu();
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }

    public void agregarCurso(CursoController cursoController) {
        System.out.println("====Inserción de nuevo curso====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String desc = scanner.nextLine();
        System.out.print("Estado (0 = Inactivo, 1 = Activo): ");
        int estado = scanner.nextInt();
        scanner.nextLine();


        CursoModel cursoModel = new CursoModel(nombre, desc, estado);
        cursoController.agregarCurso(cursoModel);
        System.out.println("Profesor insertado exitosamente.");
    }

    public void modificarCurso(CursoController cursoController) {
        System.out.println("====Modificación de cursos====");
        System.out.print("Id del curso a modificar: ");
        String idCurso = scanner.nextLine();

        CursoModel cursoModel = cursoController.consultarCurso(Integer.parseInt(idCurso));
        cursoModel.setId(cursoModel.getId());

        if (cursoModel != null) {
            System.out.println("curso encontrado: " + cursoModel);
            System.out.print("Nuevo nombre (dejar en blanco para mantener): ");
            String nuevoNombre = scanner.nextLine();
            if (!nuevoNombre.isEmpty()) {
                cursoModel.setNombre(nuevoNombre);
            }
            System.out.print("Nueva descripcion (dejar en blanco para mantener): ");
            String nuevaDesc = scanner.nextLine();
            if (!nuevaDesc.isEmpty()) {
                cursoModel.setDescripcion(nuevaDesc);
            }

            System.out.print("Nuevo Estado (0 = Inactivo, 1 = Activo, dejar en blanco para mantener): ");
            String nuevoEstadoStr = scanner.nextLine();
            if (!nuevoEstadoStr.isEmpty()) {
                int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
                cursoModel.setEstado(nuevoEstado);
            }

            cursoController.modificarCurso(cursoModel);
            System.out.println("curso modificado exitosamente.");
        } else {
            System.out.println("curso no encontrado.");
        }
    }

    public void eliminmarCurso(CursoController cursoController) {
        System.out.println("====Eliminar curso====");
        System.out.print("Id del curso: ");
        String idCurso = scanner.nextLine();

        boolean eliminado = cursoController.eliminarCurso(Integer.parseInt(idCurso));
        if (eliminado) {
            System.out.println("curso eliminado exitosamente.");
        } else {
            System.out.println("curso no encontrado.");
        }
    }

    public void consultarCurso(CursoController cursoController) {
        System.out.println("====Consulta de cursos====");
        System.out.print("Id del curso a consultar: ");
        int idCurso = scanner.nextInt();
        scanner.nextLine();

        CursoModel cursoModel = cursoController.consultarCurso(idCurso);
        if (cursoModel != null) {
            System.out.println("Curso encontrado: " + cursoModel.getNombre());
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    public void listarCurso(CursoController cursoController) {
        System.out.println("====Lista de cursos====");
        List<CursoModel> cursos = cursoController.listarCursos();
        if (!cursos.isEmpty()) {
            for (CursoModel curso : cursos) {
                System.out.println(curso.getNombre());
            }
        } else {
            System.out.println("No hay cursos disponibles.");
        }
    }
}

