import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends VentanaBase {
    private Juego juego;
    private Historial historial;
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
        historial = juego.getHistorial();
        add(historial, BorderLayout.CENTER);


        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        bolaActual = new JLabel("Bola Actual: ");
        botonSiguienteBola = new JButton("Siguiente Bola");

        botonSiguienteBola.addActionListener(e -> {
            try {
                juego.sacarBola(); // Delegamos la lógica a Juego
                repaint();
                revalidate();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, "¡No quedan más bolas en la tómbola!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panelInferior.add(bolaActual);
        panelInferior.add(botonSiguienteBola);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void sacarBola() {
        try {
            Bola bola = juego.getTombola().obtenerBola();
            historial.marcarBola(bola.getNumero()); // Actualizar el historial gráfico
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "¡No quedan más bolas en la tómbola!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
