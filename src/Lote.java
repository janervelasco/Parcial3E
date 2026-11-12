public abstract class Lote {
    protected String codigoLote;
    protected String descripcion;
    protected int pesoKilos; // Enteros positivos

    public Lote(String codigoLote, String descripcion, int pesoKilos) {
        this.codigoLote = codigoLote;
        this.descripcion = descripcion;
        this.pesoKilos = pesoKilos;
    }

    public abstract double calcularValor(); // Método abstracto polimórfico

    public String getCodigoLote() { return codigoLote; }
    public int getPesoKilos() { return pesoKilos; }
}