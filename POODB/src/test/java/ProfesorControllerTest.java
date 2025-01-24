import controller.ProfesorController;
import dao.ProfesorDAO;
import model.EstudianteModel;
import model.ProfesorModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ProfesorControllerTest {
    @Mock
        private ProfesorDAO profesorDAOMock;

    @Mock
        private ConsoleView consoleViewMock;

    @InjectMocks
        private ProfesorController profesorController;

    @BeforeEach
        public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProfesorTest() throws SQLException {
        ProfesorModel profesor = new ProfesorModel("Mario","12345","mario@mail.com","IT",1);
        doNothing().when(profesorDAOMock).agregarProfesor(profesor);
        profesorController.agregarProfesor(profesor);
        verify(consoleViewMock).showMessage("Profesor agregado correctamente");
    }

    @Test
    public void editProfesorTest() throws SQLException {
        ProfesorModel profesor = new ProfesorModel("Mario","12345","mario@mail.com","IT",1);
        doNothing().when(profesorDAOMock).actualizarProfesor(profesor);
        profesorController.modificarProfesor(profesor);
        verify(consoleViewMock).showMessage("Profesor actualizado correctamente");

    }

    @Test
    public void deleteProfesorTest() throws SQLException {
        int id = 1;
        doNothing().when(profesorDAOMock).eliminarProfesor(id);
        profesorController.eliminarProfesor(id);
        verify(consoleViewMock).showMessage("Profesor eliminado correctamente");
    }

    @Test
    public void listProfesorTest() throws SQLException {
        List<ProfesorModel> profesores = Arrays.asList(
                new ProfesorModel("Pepe", "12344","pepe@mail.com","IT",1),
                new ProfesorModel("Pepe", "12344345","pepe@mail.com","IT",0)
        );
        when(profesorDAOMock.listarProfesores()).thenReturn(profesores);
        List<ProfesorModel> profesorModels = profesorController.listarProfesores();
        verify(consoleViewMock).showMessage("Profesores consultados correctamente");
    }
}