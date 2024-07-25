
import controller.ConexionController;
import model.conexion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ConexionControllerTest {
    @Mock
            private ConsoleView mockView;

    @Mock
            private Connection connectionMock;

    @InjectMocks
            private ConexionController controller;

    @BeforeEach
            public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test() throws SQLException {
        when(conexion.getConnection()).thenReturn(connectionMock);
        doNothing().when(connectionMock).close();

        controller.openConnection();
        verify(connectionMock).close();
        verify(mockView).showMessage("Conexion Establecida");

    }
}

