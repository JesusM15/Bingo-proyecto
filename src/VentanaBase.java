import javax.swing.*;
import java.awt.*;

public abstract class VentanaBase extends JFrame {
    public VentanaBase(String titulo) {
        setTitle(titulo);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }

    protected abstract void inicializarComponentes();

    public void mostrar() {
        setVisible(true);
    }

    public void ocultar() {
        setVisible(false);
    }

}
