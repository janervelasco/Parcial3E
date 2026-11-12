import javax.swing.*;
import java.time.LocalDate;

public class Main {
    // Instancia única del gestor principal de EcoRuta
    private static GestorEcoRuta gestorEcoRuta = new GestorEcoRuta();

    public static void main(String[] args) {
        int opcionPrincipal = 0;
        do {
            String entradaOpcionPrincipal = JOptionPane.showInputDialog(
                    "\n--- MENÚ PRINCIPAL ECORUTA ---\n" +
                            "1. Menú de Registros\n" +
                            "2. Gestión de Entregas y Lotes\n" +
                            "3. Consultar Información\n" +
                            "0. Salir del sistema"
            );
            if (entradaOpcionPrincipal == null) {
                break;
            }
            opcionPrincipal = Integer.parseInt(entradaOpcionPrincipal);

            switch (opcionPrincipal) {
                case 1:
                    menuRegistros();
                    break;
                case 2:
                    menuGestionEntregas();
                    break;
                case 3:
                    menuConsultas();
                    break;
                case 0:
                    JOptionPane.showMessageDialog(null, "¡Saliendo del sistema EcoRuta!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
                    break;
            }
        } while (opcionPrincipal != 0);
    }

    private static void menuRegistros() {
        String entradaOpcionRegistros = JOptionPane.showInputDialog(
                "--- MENÚ DE REGISTROS ---\n" +
                        "1. Registrar Aportante\n" +
                        "2. Registrar Nueva Entrega\n" +
                        "0. Volver"
        );
        if (entradaOpcionRegistros == null) {
            return;
        }
        int opcionRegistros = Integer.parseInt(entradaOpcionRegistros);

        switch (opcionRegistros) {
            case 1:
                registrarAportante();
                break;
            case 2:
                registrarEntrega();
                break;
            case 0:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                break;
        }
    }

    private static void registrarAportante() {
        String identificacion = JOptionPane.showInputDialog("ID / Cédula del aportante:");
        if (identificacion == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre completo:");
        if (nombre == null) return;
        String direccion = JOptionPane.showInputDialog("Dirección:");
        if (direccion == null) return;
        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) return;

        Aportante aportante = new Aportante(identificacion, nombre, direccion, telefono);
        // Opcional: si manejas lista de aportantes en tu gestor, los registras aquí.
        JOptionPane.showMessageDialog(null, "¡Aportante creado con éxito (Listo para asociar a entregas)!");
    }

    private static void registrarEntrega() {
        String codigoEntrega = JOptionPane.showInputDialog("Código único de la entrega (ej. ENT-001):");
        if (codigoEntrega == null) return;

        String identificacion = JOptionPane.showInputDialog("Cédula del aportante:");
        if (identificacion == null) return;
        String nombre = JOptionPane.showInputDialog("Nombre del aportante:");
        if (nombre == null) return;
        String direccion = JOptionPane.showInputDialog("Dirección:");
        if (direccion == null) return;
        String telefono = JOptionPane.showInputDialog("Teléfono:");
        if (telefono == null) return;

        Aportante aportante = new Aportante(identificacion, nombre, direccion, telefono);
        Entrega nuevaEntrega = new Entrega(codigoEntrega, aportante);

        if (gestorEcoRuta.registrarEntrega(nuevaEntrega)) {
            JOptionPane.showMessageDialog(null, "¡Entrega registrada con éxito! (Estado inicial: ABIERTA)");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Ya existe una entrega con ese código.");
        }
    }

    private static void menuGestionEntregas() {
        String entradaOpcionGestion = JOptionPane.showInputDialog(
                "--- GESTIÓN DE ENTREGAS Y LOTES ---\n" +
                        "1. Agregar Lote a una Entrega\n" +
                        "2. Aceptar Entrega (Generar Comprobante)\n" +
                        "0. Volver"
        );
        if (entradaOpcionGestion == null) {
            return;
        }
        int opcionGestion = Integer.parseInt(entradaOpcionGestion);

        if (opcionGestion == 0) {
            return;
        }

        String codigoEntrega = JOptionPane.showInputDialog("Ingrese el código de la entrega:");
        if (codigoEntrega == null) return;
        Entrega entregaEncontrada = gestorEcoRuta.buscarEntregaPorCodigo(codigoEntrega);

        if (entregaEncontrada == null) {
            JOptionPane.showMessageDialog(null, "No se encontró ninguna entrega con ese código.");
            return;
        }

        switch (opcionGestion) {
            case 1:
                if (entregaEncontrada.getEstado() != EstadoEntrega.ABIERTA) {
                    JOptionPane.showMessageDialog(null, "Esta entrega ya fue procesada/aceptada y no admite más lotes.");
                    break;
                }

                String tipoLoteTexto = JOptionPane.showInputDialog(
                        "Seleccione el tipo de Lote:\n" +
                                "1. Papel y Cartón\n" +
                                "2. Metal\n" +
                                "3. Electrónico"
                );
                if (tipoLoteTexto == null) break;
                int tipoLote = Integer.parseInt(tipoLoteTexto);

                String codigoLote = JOptionPane.showInputDialog("Código del lote:");
                if (codigoLote == null) break;
                String descripcion = JOptionPane.showInputDialog("Descripción del lote:");
                if (descripcion == null) break;
                int pesoKilos = Integer.parseInt(JOptionPane.showInputDialog("Peso en kilos:"));

                boolean agregado = false;

                if (tipoLote == 1) {
                    boolean clasificado = Boolean.parseBoolean(JOptionPane.showInputDialog("¿Está clasificado? (true/false):"));
                    LotePapelCarton lotePapel = new LotePapelCarton(codigoLote, descripcion, pesoKilos, clasificado);
                    agregado = entregaEncontrada.agregarLote(lotePapel);
                } else if (tipoLote == 2) {
                    String tipoMetalStr = JOptionPane.showInputDialog("Tipo de metal (ALUMINIO / ACERO):").toUpperCase();
                    TipoMetal tipoMetal = TipoMetal.valueOf(tipoMetalStr);
                    LoteMetal loteMetal = new LoteMetal(codigoLote, descripcion, pesoKilos, tipoMetal);
                    agregado = entregaEncontrada.agregarLote(loteMetal);
                } else if (tipoLote == 3) {
                    int equiposCompletos = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de equipos completos:"));
                    LoteElectronico loteElectro = new LoteElectronico(codigoLote, descripcion, pesoKilos, equiposCompletos);
                    agregado = entregaEncontrada.agregarLote(loteElectro);
                }

                if (agregado) {
                    JOptionPane.showMessageDialog(null, "¡Lote agregado con éxito a la entrega!");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo agregar el lote (verifique el peso o código duplicado).");
                }
                break;

            case 2:
                if (entregaEncontrada.getEstado() == EstadoEntrega.ACEPTADA) {
                    JOptionPane.showMessageDialog(null, "Esta entrega ya se encuentra ACEPTADA.");
                } else {
                    boolean aceptada = entregaEncontrada.aceptarEntrega();
                    if (aceptada) {
                        JOptionPane.showMessageDialog(null,
                                "¡Entrega aceptada con éxito!\n\n" +
                                        "--- COMPROBANTE ---\n" +
                                        "Código: " + entregaEncontrada.getComprobante().codigoEntrega() + "\n" +
                                        "Peso Total: " + entregaEncontrada.getComprobante().pesoTotal() + " kg\n" +
                                        "Valor Total a Pagar: $" + entregaEncontrada.getComprobante().valorTotal()
                        );
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo aceptar la entrega (debe contener al menos un lote).");
                    }
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                break;
        }
    }

    private static void menuConsultas() {
        String entradaOpcionConsultas = JOptionPane.showInputDialog(
                "--- MENÚ DE CONSULTAS ---\n" +
                        "1. Consultar Detalles de Entrega\n" +
                        "2. Consultar Recaudo Total del Sistema\n" +
                        "0. Volver"
        );
        if (entradaOpcionConsultas == null) {
            return;
        }
        int opcionConsultas = Integer.parseInt(entradaOpcionConsultas);

        switch (opcionConsultas) {
            case 1:
                consultarEntrega();
                break;
            case 2:
                double recaudoTotal = gestorEcoRuta.calcularRecaudoTotalAceptado();
                JOptionPane.showMessageDialog(null, "El recaudo total acumulado del sistema es: $" + recaudoTotal);
                break;
            case 0:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida.");
                break;
        }
    }

    private static void consultarEntrega() {
        String codigoConsulta = JOptionPane.showInputDialog("Ingrese el código de la entrega a consultar:");
        if (codigoConsulta == null) return;

        Entrega entrega = gestorEcoRuta.buscarEntregaPorCodigo(codigoConsulta);
        if (entrega != null) {
            JOptionPane.showMessageDialog(null,
                    "--- INFORMACIÓN DE LA ENTREGA ---\n" +
                            "Código: " + entrega.getCodigoEntrega() + "\n" +
                            "Aportante: " + entrega.getAportante().nombre() + " (CC: " + entrega.getAportante().identificacion() + ")\n" +
                            "Estado: " + entrega.getEstado() + "\n" +
                            "Total Lotes: " + entrega.getListaLotes().size() + "\n" +
                            "Peso Total: " + entrega.calcularPesoTotal() + " kg\n" +
                            "Valor Estimado: $" + entrega.calcularValorTotal()
            );
        } else {
            JOptionPane.showMessageDialog(null, "Entrega no encontrada.");
        }
    }
}