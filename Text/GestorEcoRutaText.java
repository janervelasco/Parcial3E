import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GestorEcoRutaTest {

    private GestorEcoRuta gestor;
    private Entrega entrega;

    @BeforeEach
    void setUp() {
        gestor = new GestorEcoRuta();
        Aportante aportante = new Aportante("98765", "Ana Gómez", "Laureles");
        entrega = new Entrega("ENT-999", aportante, new ArrayList<>());
        gestor.agregarEntrega(entrega);
    }

    @Test
    void buscarEntregaPorCodigoExistente() {
        Entrega encontrada = gestor.buscarEntregaPorCodigo("ENT-999");
        assertNotNull(encontrada, "La entrega debería ser encontrada en el sistema");
        assertEquals("ENT-999", encontrada.getCodigoEntrega());
    }

    @Test
    void buscarEntregaPorCodigoInexistente() {
        Entrega encontrada = gestor.buscarEntregaPorCodigo("ENT-404");
        assertNull(encontrada, "No debería retornar ninguna entrega para un código desconocido");
    }
}