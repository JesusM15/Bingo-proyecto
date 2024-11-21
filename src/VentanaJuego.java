import javax.swing.*;
import java.awt.*;

public class VentanaJuego extends VentanaBase {
    private Juego juego;
    private Historial historial;
    private JLabel bolaActual;
    private JButton botonSiguienteBola;
    private JButton botonBingo;

    public VentanaJuego(Juego juego) {
        super("Juego de Bingo");
        this.juego = juego;
        inicializarComponentes();
    }

    @Override
    protected void inicializarComponentes() {
        // Usar el historial lógico desde el juego para crear el gráfico
        historial = juego.getHistorial();
        // agregar al panel principal o ventana el historial centrado
        add(historial, BorderLayout.CENTER);

        // crear panel que sera el contenedor de los botones de abajo
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout());

        bolaActual = new JLabel("Bola Actual: ");

        // boton para bingo
        botonBingo = new JButton("BINGO!");

        botonBingo.addActionListener(e -> {
            juego.verificarCarta();
            //            JOptionPane.showMessageDialog(null, "BINGO!!!");
        });

        // boton para obtener la siguiente bola
        botonSiguienteBola = new JButton("Siguiente Bola");
        // crear el listener(funcion al clickear del boton)
        botonSiguienteBola.addActionListener(e -> {
            try {
                juego.sacarBola(); // Delegamos la lógica a Juego
                repaint();
                revalidate();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, "¡No quedan más bolas en la tómbola!", "Fin del Juego", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // agregar los elementos en el panel inferior
        panelInferior.add(bolaActual);
        panelInferior.add(botonSiguienteBola);
        panelInferior.add(botonBingo);

        // agregar el panel inferior en la posicion baja de la ventana o panel principal.
        add(panelInferior, BorderLayout.SOUTH);
    }

}
