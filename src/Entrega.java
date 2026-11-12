
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private String codigoEntrega;
    private LocalDate fecha;
    private Aportante aportante;
    private List<Lote> listaLotes;
    private EstadoEntrega estado;
    private Comprobante comprobante;

    public Entrega(String codigoEntrega, Aportante aportante) {
        this.codigoEntrega = codigoEntrega;
        this.fecha = LocalDate.now();
        this.aportante = aportante;
        this.listaLotes = new ArrayList<>();
        this.estado = EstadoEntrega.ABIERTA; // Inicia en ABIERTA sin lotes
        this.comprobante = null;
    }

    public boolean agregarLote(Lote nuevoLote) {
        // Validar que esté abierta
        if (estado != EstadoEntrega.ABIERTA) {
            return false;
        }
        // Validar peso válido (positivo)
        if (nuevoLote.getPesoKilos() <= 0) {
            return false;
        }
        // Validar código sin repetir dentro de la entrega
        for (Lote l : listaLotes) {
            if (l.getCodigoLote().equalsIgnoreCase(nuevoLote.getCodigoLote())) {
                return false;
            }
        }
        listaLotes.add(nuevoLote);
        return true;
    }

    public boolean aceptarEntrega() {
        // Si no está abierta o está vacía (sin lotes), se rechaza
        if (estado != EstadoEntrega.ABIERTA || listaLotes.isEmpty()) {
            return false;
        }

        int pesoTotal = calcularPesoTotal();
        double valorTotal = calcularValorTotal();

        // Generar comprobante inmutable
        this.comprobante = new Comprobante(codigoEntrega, aportante.identificacion(), pesoTotal, valorTotal);
        this.estado = EstadoEntrega.ACEPTADA; // Pasa a ACEPTADA y se cierra a nuevos lotes
        return true;
    }

    public int calcularPesoTotal() {
        int total = 0;
        for (Lote l : listaLotes) {
            total += l.getPesoKilos();
        }
        return total;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Lote l : listaLotes) {
            total += l.calcularValor(); // Cálculo polimórfico
        }
        return total;
    }

    public List<String> obtenerEtiquetas() {
        List<String> etiquetas = new ArrayList<>();
        for (Lote l : listaLotes) {
            if (l instanceof Etiquetable) {
                etiquetas.add(((Etiquetable) l).generarEtiqueta());
            }
        }
        return etiquetas;
    }

    // Getters
    public String getCodigoEntrega() {
        return codigoEntrega;
    }

    public Aportante getAportante() {
        return aportante;
    }

    public EstadoEntrega getEstado() {
        return estado;
    }

    public Comprobante getComprobante() {
        return comprobante;
    }
    //HHGHG
}