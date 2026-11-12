
public class LotePapelCarton extends Lote {
    private boolean clasificado;

    public LotePapelCarton(String codigoLote, String descripcion, int pesoKilos, boolean clasificado) {
        super(codigoLote, descripcion, pesoKilos);
        this.clasificado = clasificado;
    } //C

    @Override
    public double calcularValor() {
        // $500 por kg, más $100 por kg si está clasificado
        double tarifaBase = 500.0;
        if (clasificado) {
            tarifaBase += 100.0;
        }
        return pesoKilos * tarifaBase;
    }
}