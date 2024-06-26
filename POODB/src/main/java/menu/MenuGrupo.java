package menu;

import controller.EstudianteController;
import controller.GrupoController;
import model.CursoModel;
import model.GrupoModel;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuGrupo {
    private Scanner scanner;
    private boolean exit;
    Menu menu;

    public MenuGrupo() {
        scanner = new Scanner(System.in);
        exit = false;
    }

    public void showMenuGrupo() throws SQLException {
        ConsoleView consoleView = new ConsoleView();
        GrupoController grupoController = new GrupoController(consoleView);

        while (!exit) {
            System.out.println("====Menu de grupos====");
            System.out.println("1. Inserción de nuevos grupos.");
            System.out.println("2. Modificación de grupos existentes.");
            System.out.println("3. Eliminación de grupos.");
            System.out.println("4. Consulta de grupos.");
            System.out.println("5. Listar grupos.");
            System.out.println("0. Salir.");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    agregarGrupo(grupoController);
                    break;
                case 2:
                    modificarGrupo(grupoController);
                    break;
                case 3:
                    eliminarGrupo(grupoController);
                    break;
                case 4:
                    consultarGrupo(grupoController);
                    break;
                case 5:
                    listarGrupos(grupoController);
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

    public void agregarGrupo(GrupoController grupoController) {
        System.out.println("====Inserción de nuevo grupo====");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Descripcion: ");
        String desc = scanner.nextLine();
        System.out.print("Estado (0 = Inactivo, 1 = Activo): ");
        int estado = scanner.nextInt();
        scanner.nextLine();


        GrupoModel grupoModel = new GrupoModel(nombre, desc, estado);
        grupoController.agregarGrupo(grupoModel);
        System.out.println("grupo insertado exitosamente.");
    }

    public void modificarGrupo(GrupoController grupoController) {
        System.out.println("====Modificación de grupos====");
        System.out.print("Id del grupo a modificar: ");
        String idGrupo = scanner.nextLine();

        GrupoModel grupoModel = grupoController.consultarGrupo(Integer.parseInt(idGrupo));
        grupoModel.setId(grupoModel.getId());

        System.out.println("grupo encontrado: " + grupoModel);
        System.out.print("Nuevo nombre (dejar en blanco para mantener): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            grupoModel.setNombre(nuevoNombre);
        }
        System.out.print("Nueva descripcion (dejar en blanco para mantener): ");
        String nuevaDesc = scanner.nextLine();
        if (!nuevaDesc.isEmpty()) {
            grupoModel.setDescripcion(nuevaDesc);
        }

        System.out.print("Nuevo Estado (0 = Inactivo, 1 = Activo, dejar en blanco para mantener): ");
        String nuevoEstadoStr = scanner.nextLine();
        if (!nuevoEstadoStr.isEmpty()) {
            int nuevoEstado = Integer.parseInt(nuevoEstadoStr);
            grupoModel.setEstado(nuevoEstado);
        }

        grupoController.modificarGrupo(grupoModel);
        System.out.println("Grupo modificado exitosamente.");
    }

    private void eliminarGrupo(GrupoController grupoController) {
        System.out.println("====Eliminación de Grupos====");
        System.out.print("Identificación del grupo a eliminar: ");
        String idGrupo = scanner.nextLine();

        boolean eliminado = grupoController.eliminarGrupo(Integer.parseInt(idGrupo));
        if (eliminado) {
            System.out.println("grupo eliminado exitosamente.");
        } else {
            System.out.println("grupo no encontrado.");
        }
    }

    private void consultarGrupo(GrupoController grupoController) {
        System.out.println("====Consulta de grupos====");
        System.out.print("Id del grupo a consultar: ");
        int idGrupo = scanner.nextInt();
        scanner.nextLine();

        GrupoModel grupoModel = grupoController.consultarGrupo(idGrupo);
        if (grupoModel != null) {
            System.out.println("Grupo encontrado: " + grupoModel);
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    private void listarGrupos(GrupoController grupoController) {
        System.out.println("====Consulta de Grupos====");
        List<GrupoModel> grupos = grupoController.listarGrupos();
        if (grupos != null || !grupos.isEmpty()) {
            for (GrupoModel grupoModel : grupos) {
                System.out.println(grupoModel);
            }
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }
}

