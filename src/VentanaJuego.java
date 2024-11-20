import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends VentanaBase {
    private Juego juego;
    private HistorialGrafico historialGrafico;
    private JLabel bolaActual;
    private JButton botonSiguienteBola;

    public VentanaJuego(Juego juego) {
        super("Juego de Bingo");
        this.juego = juego;
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        // Usar el historial lógico desde el juego para crear el gráfico
        historialGrafico = new HistorialGrafico(juego.getHistorial());
        add(historialGrafico, BorderLayout.CENTER);

        // Panel inferior para controles
        JPanel panelInferior = new JPanel(new BorderLayout());

        // Mostrar bola actual
        bolaActual = new JLabel("Bola Actual: --", SwingConstants.CENTER);
        bolaActual.setFont(new Font("Arial", Font.BOLD, 24));
        bolaActual.setOpaque(true);
        bolaActual.setBackground(Color.WHITE);
        bolaActual.setForeground(Color.BLACK);
        panelInferior.add(bolaActual, BorderLayout.CENTER);

        // Botón de siguiente bola
        botonSiguienteBola = new JButton("Siguiente Bola");
        botonSiguienteBola.addActionListener(e -> sacarBola());
        panelInferior.add(botonSiguienteBola, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    private void sacarBola() {
        try {
            Bola bola = juego.getTombola().obtenerBola();
            historialGrafico.marcarBola(bola.getNumero()); // Actualizar el historial gráfico
            bolaActual.setText("Bola Actual: " + bola.getColumna() + bola.getNumero());
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "¡No quedan más bolas en la tómbola!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
