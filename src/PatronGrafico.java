import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PatronGrafico extends Patron {
    private JPanel panel; // Panel que contiene la vista y el botón
    private JButton botonSeleccionar;

    public PatronGrafico(String nombre, boolean[][] patron) {
        super(nombre, patron);
        panel = new JPanel(new BorderLayout());
        botonSeleccionar = new JButton("Seleccionar");

        configurarPanel();
    }

    private void configurarPanel() {
        // Panel para vista del patrón
        JPanel vista = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujar(g, 10, 10, 20);
            }
        };
        vista.setPreferredSize(new Dimension(120, 120));
        vista.setBackground(Color.WHITE);

        // Añadir componentes al panel principal
        panel.add(vista, BorderLayout.CENTER);
        panel.add(botonSeleccionar, BorderLayout.SOUTH);
    }

    public void setActionListener(ActionListener action) {
        botonSeleccionar.addActionListener(action);
    }

    public JPanel getPanel() {
        return panel;
    }
}
