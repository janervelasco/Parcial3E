
import java.util.ArrayList;
import java.util.List;

public class GestorEcoRuta {
    private List<Entrega> listaEntregas;

    public GestorEcoRuta() {
        this.listaEntregas = new ArrayList<>();
    }

    public boolean registrarEntrega(Entrega nuevaEntrega) {
        if (nuevaEntrega == null) {
            return false;
        }
        for (Entrega entregaExistente : listaEntregas) {
            if (entregaExistente.getCodigoEntrega().equalsIgnoreCase(nuevaEntrega.getCodigoEntrega())) {
                return false;
            }
        }
        listaEntregas.add(nuevaEntrega);
        return true;
    }

    public Entrega buscarEntregaPorCodigo(String codigoEntrega) {
        for (Entrega entrega : listaEntregas) {
            if (entrega.getCodigoEntrega().equalsIgnoreCase(codigoEntrega)) {
                return entrega;
            }
        }
        return null;
    }

    public double calcularRecaudoTotalAceptado() {
        double totalRecaudado = 0.0;
        for (Entrega entrega : listaEntregas) {
            if (entrega.getEstado() == EstadoEntrega.ACEPTADA && entrega.getComprobante() != null) {
                totalRecaudado += entrega.getComprobante().valorTotal();
            }
        }
        return totalRecaudado;
    }

    public List<Entrega> getListaEntregas() {
        return listaEntregas;
    }
}