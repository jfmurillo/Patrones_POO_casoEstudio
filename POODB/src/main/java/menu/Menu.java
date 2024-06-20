package menu;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private boolean exit;

    public Menu() {
        scanner = new Scanner(System.in);
        exit = false;
    }

    public void showMenu() throws SQLException {
        while (!exit) {
            System.out.println("====Caso de estudio Conocimientos en Programación Orientada a Objetos====");
            System.out.println("1. Estudiante");
            System.out.println("2. Profesor");
            System.out.println("3. Grupo");
            System.out.println("4. Curso");
            System.out.print("Seleccione una opción: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Has seleccionado la Opción 1");
                    MenuEstudiante est = new MenuEstudiante();
                    est.showMenuEstudiante();
                    break;
                case 2:
                    System.out.println("Has seleccionado la Opción 2");
                    MenuProfesor prof = new MenuProfesor();
                    prof.showMenuProfesor();
                    break;
                case 3:
                    System.out.println("Has seleccionado la Opción 3");
                    MenuGrupo g = new MenuGrupo();
                    g.showMenuGrupo();
                    break;
                case 4:
                    System.out.println("Has seleccionado la Opción 4");
                    MenuCurso cur = new MenuCurso();
                    cur.showMenuCurso();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida, por favor intenta de nuevo.");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
