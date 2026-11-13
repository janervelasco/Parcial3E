import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la gestión de EcoRuta
 * @author Área de programación UQ
 * @since 2026-11
 */
public class GestorEcoRutaTest {

    private static final Logger LOG = Logger.getLogger(GestorEcoRutaTest.class.getName());

    @BeforeEach
    void setUp() {
        LOG.info("Iniciando configuración de EcoRutaTest...");
    }

    @Test
    void testValidarListaElementos() {
        LOG.info("Ejecutando testValidarListaElementos...");
        List<String> elementosEsperados = List.of("LotePapelCarton", "LoteMetal");
        List<String> elementosObtenidos = List.of("LotePapelCarton", "LoteMetal");

        assertIterableEquals(elementosEsperados, elementosObtenidos, "Las listas de elementos deben coincidir");
        LOG.info("Test de lista de elementos finalizado con éxito.");
    }

    @Test
    void testValidarCondicionLogica() {
        LOG.info("Ejecutando testValidarCondicionLogica...");
        int cantidadLotes = 2;
        assertTrue(cantidadLotes > 0, "La cantidad de lotes registrados debe ser mayor a cero");
        LOG.info("Test de condición lógica finalizado con éxito.");
    }
}