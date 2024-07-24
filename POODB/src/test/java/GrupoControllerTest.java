import controller.GrupoController;
import dao.GrupoDAO;
import model.GrupoModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GrupoControllerTest {
    @Mock
            private GrupoDAO grupoDAOMock;
    @Mock
            private ConsoleView consoleViewMock;
    @InjectMocks
    private GrupoController grupoController;

    @BeforeEach
            public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddGrupo() throws SQLException {
        String nombre = "Grupo 1";
        String descripcion = "Grupo 2 desc";
        int estado = 1;

        doNothing().when(grupoDAOMock).agregarGrupo(any(GrupoModel.class));

        GrupoModel grupoModel = new GrupoModel(nombre, descripcion, estado);
        grupoController.agregarGrupo(grupoModel);
        verify(consoleViewMock).showMessage("Datos insertados");
    }

    @Test
    public void testEditGrupo() throws SQLException {
        GrupoModel grupo= new GrupoModel("Grupo 2", "N/A", 1);
        doNothing().when(grupoDAOMock).modificarGrupo(grupo);

        grupoController.modificarGrupo(grupo);
        verify(consoleViewMock).showMessage("Datos modificados");
    }

    @Test
    public void testDeleteGrupo() throws SQLException {
        int idGrupo = 1;
        doNothing().when(grupoDAOMock).eliminacionGrupo(idGrupo);

        grupoController.eliminarGrupo(idGrupo);
        verify(consoleViewMock).showMessage("Datos eliminados");
    }

    @Test
    public void testListarGrupo() throws SQLException {
        List<GrupoModel> grupos = Arrays.asList(
                new GrupoModel("Grupo 3","Desc",0),
                new GrupoModel("Grrupo 2","Desc 2",1)
        );
        when(grupoDAOMock.listarGrupos()).thenReturn(grupos);
        List<GrupoModel> grupoModels = grupoController.listarGrupos();
        verify(consoleViewMock).showMessage("Datos consultados");
    }

}