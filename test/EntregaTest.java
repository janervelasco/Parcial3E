import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la entidad Entrega / Lote
 * @author Área de programación UQ
 * @since 2026-11
 */
public class EntregaTest {

    private static final Logger LOG = Logger.getLogger(EntregaTest.class.getName());

    @BeforeEach
    void setUp() {
        LOG.info("Iniciando configuración de EntregaTest...");
    }

    @Test
    void calcularPesoTotal() {
        LOG.info("Ejecutando test calcularPesoTotal...");
        double pesoTotal = 10.0; // Reemplaza con la llamada a tu método real
        assertEquals(10.0, pesoTotal, 0.01, "El peso total de la entrega debe ser 10.0 kg");
        LOG.info("Test calcularPesoTotal finalizado con éxito.");
    }

    @Test
    void calcularValorTotal() {
        LOG.info("Ejecutando test calcularValorTotal...");
        double valorEsperado = 10.0 * 500; // Supuesto valor base por kg
        double valorCalculado = 5000.0; // Reemplaza con la llamada a tu método real
        assertEquals(valorEsperado, valorCalculado, 0.01, "El valor estimado debe coincidir con el cálculo");
        LOG.info("Test calcularValorTotal finalizado con éxito.");
    }
}