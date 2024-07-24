import controller.CursoController;
import dao.CursoDAO;
import model.CursoModel;
import model.CursoModel.*;
import dao.CursoDAO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;
import view.ConsoleView.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

public class CursoControllerTest{
    @Mock
            private CursoDAO daoMock;

    @Mock
            private ConsoleView mockView;

    @InjectMocks
            private CursoController cursoController;

    @BeforeEach
            public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarCurso() throws SQLException { // FUNCIONAAAAAAAAAAAAAAAAA
        //CursoModel curso = new CursoModel("Curso de Java","Cuatri 2" , 1);
        String nombre = "Typrescript Mock";
        String descripcion = "Prueba";
        int estado = 0;
        //doNothing().when(daoMock).agregarCurso(curso);
        doNothing().when(daoMock).agregarCurso(any(CursoModel.class));

        //cursoController.agregarCurso(curso);
        CursoModel curso = new CursoModel(nombre, descripcion, estado);
        cursoController.agregarCurso(curso);
        //verify(daoMock).agregarCurso(curso);
        verify(mockView).showMessage("Datos insertados");
    }

    @Test
    public void testModificarCurso() throws SQLException {
        CursoModel curso = new CursoModel("Curso de Javascript","Intro Progra" , 1);
        doNothing().when(daoMock).modificarCurso(curso);

        cursoController.modificarCurso(curso);

        //verify(daoMock).modificarCurso(curso);
        verify(mockView).showMessage("Datos modificados correctamente");
    }

    @Test
    public void testEliminarCurso() throws SQLException {
        int id = 1;
        doNothing().when(daoMock).eliminacionCurso(id);

        cursoController.eliminarCurso(id);

        //verify(daoMock).eliminacionCurso(id);
        verify(mockView).showMessage("Datos eliminados correctamente");
    }


    @Test
    public void testListarCursos() throws SQLException {
        List<CursoModel> cursos = Arrays.asList(
                new CursoModel("Curso de Java", "Descripción del curso de Java",1 ),
                new CursoModel("Curso de Python", "Descripción del curso de python", 0)
        );
        when(daoMock.listarCursos()).thenReturn(cursos);

        List<CursoModel> result = cursoController.listarCursos();

        //verify(daoMock).listarCursos();
        verify(mockView).showMessage("Datos de los cursos consultados correctamente");
        //assertEquals(cursos, result);
    }

}