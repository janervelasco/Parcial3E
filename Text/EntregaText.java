import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntregaTest {

    private Entrega entrega;
    private Aportante aportante;

    @BeforeEach
    void setUp() {
        aportante = new Aportante("12345", "Carlos Pérez", "Barrio Centro");
        List<Lote> lotes = new ArrayList<>();
        lotes.add(new LotePapelCarton("L001", 10.0, true)); // 10 kg, limpio
        entrega = new Entrega("ENT-001", aportante, lotes);
    }

    @Test
    void calcularPesoTotal() {
        double pesoTotal = entrega.calcularPesoTotal();
        assertEquals(10.0, pesoTotal, 0.01, "El peso total de la entrega debe ser 10.0 kg");
    }

    @Test
    void calcularValorTotal() {
        double valorEsperado = 10.0 * 500; // Supuesto valor base por kg de papel/cartón
        double valorCalculado = entrega.calcularValorTotal();
        assertEquals(valorEsperado, valorCalculado, 0.01, "El valor estimado debe coincidir con el cálculo");
    }
}