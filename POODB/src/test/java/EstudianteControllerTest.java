import controller.EstudianteController;
import dao.EstudianteDAO;
import model.EstudianteModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;


import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class EstudianteControllerTest {
    @Mock
    private EstudianteDAO estudianteDAOMock;
    @Mock
    private ConsoleView consoleViewMock;
    @InjectMocks
    private EstudianteController estudianteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addEstudianteTest() throws SQLException {
        EstudianteModel estudiante = new EstudianteModel( "Joseph", "12345", "joseph@example.com", Date.valueOf("2000-01-01"), 1);
        doNothing().when(estudianteDAOMock).agregarEstudiante(estudiante);
        estudianteController.agregarEstudiante(estudiante);

        verify(consoleViewMock).showMessage("Datos agregados correctamente");
    }

    @Test
    public void editEstudianteTest() throws SQLException {
        EstudianteModel estudiante = new EstudianteModel( "Joseph", "117920465", "joseph@example.com", Date.valueOf("2000-01-01"), 1);
        doNothing().when(estudianteDAOMock).modificarEstudiante(estudiante);
        estudianteController.modificarEstudiante(estudiante);
        verify(consoleViewMock).showMessage("Datos modificados correctamente");
    }

    @Test
    public void deleteEstudianteTest() throws SQLException {
        int idEstudiante = 1;
        doNothing().when(estudianteDAOMock).eliminarEstudiante(idEstudiante);
        estudianteController.eliminarEstudiante(idEstudiante);
        verify(consoleViewMock).showMessage("Estudiante eliminado correctamente");
    }

    @Test
    public void getAllEstudiantesTest() throws SQLException {
        List<EstudianteModel> estudiantes = Arrays.asList(
                new EstudianteModel("Pepe","117920465", "pepe@mail.com", Date.valueOf("2000-01-01"), 0),
                new EstudianteModel("Joseph","12345678", "joseph@mail.com", Date.valueOf("2000-01-01"), 1)
        );
        when(estudianteDAOMock.listarEstudiante()).thenReturn(estudiantes);
        List<EstudianteModel> estudianteModels = estudianteController.listarEstudiantes();
        verify(consoleViewMock).showMessage("Estudiantes encontrados");
    }
}