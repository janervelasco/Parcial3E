

public class LoteMetal extends Lote implements Etiquetable {
    private TipoMetal tipoMetal;

    public LoteMetal(String codigoLote, String descripcion, int pesoKilos, TipoMetal tipoMetal) {
        super(codigoLote, descripcion, pesoKilos);
        this.tipoMetal = tipoMetal;
    }

    @Override
    public double calcularValor() {
        // $2.200 por kg de aluminio y $900 por kg de acero
        double precioPorKg = (tipoMetal == TipoMetal.ALUMINIO) ? 2200.0 : 900.0;
        return pesoKilos * precioPorKg;
    }

    @Override
    public String generarEtiqueta() {
        return "Etiqueta Metal - Código Lote: " + codigoLote + " | Tipo: " + tipoMetal;
    }
}
