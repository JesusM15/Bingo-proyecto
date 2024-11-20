import javax.swing.*;
import java.awt.*;

public class VentanaConfiguracion extends VentanaBase {
    private GestorDePatrones gestorPatrones;
    private Juego juego;

    public VentanaConfiguracion(Juego juego) {
        super("Configuración de Bingo");
        this.juego = juego;
        this.gestorPatrones = new GestorDePatrones();
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        JLabel titulo = new JLabel("Selecciona un patrón de juego:");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel principal para los patrones gráficos
        JPanel panelPatrones = new JPanel();
        panelPatrones.setLayout(new GridLayout(0, 4, 10, 10)); // 4 patrones por fila con espacio

        for (Patron patron : gestorPatrones.getPatronesDisponibles()) {
            PatronGrafico patronGrafico = new PatronGrafico(patron.getNombre(), patron.getPatron());

            // Configurar acción para el botón de selección
            patronGrafico.setActionListener(e -> {
                juego.setPatronSeleccionado(patron); // Informar al juego del patrón seleccionado
                JOptionPane.showMessageDialog(
                        this,
                        "Has seleccionado el patrón: " + patron.getNombre(),
                        "Patrón Seleccionado",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose(); // Cierra la ventana de configuración
            });

            panelPatrones.add(patronGrafico.getPanel());
        }

        JScrollPane scrollPanel = new JScrollPane(panelPatrones);
        add(scrollPanel, BorderLayout.CENTER);
    }
}
