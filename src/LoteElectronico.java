public class LoteElectronico extends Lote implements Etiquetable {
    private int cantidadEquiposCompletos;

    public LoteElectronico(String codigoLote, String descripcion, int pesoKilos, int cantidadEquiposCompletos) {
        super(codigoLote, descripcion, pesoKilos);
        this.cantidadEquiposCompletos = cantidadEquiposCompletos;
    }

    @Override
    public double calcularValor() {
        // $1.400 por kilogramo, más $800 por cada equipo completo
        return (pesoKilos * 1400.0) + (cantidadEquiposCompletos * 800.0);
    }

    @Override
    public String generarEtiqueta() {
        return "Etiqueta Electrónico - Código Lote: " + codigoLote + " | Equipos Completos: " + cantidadEquiposCompletos;
    }
}
