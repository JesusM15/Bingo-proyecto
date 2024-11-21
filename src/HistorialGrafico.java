import javax.swing.*;
import java.awt.*;

public abstract class HistorialGrafico extends JPanel{
    private JLabel[][] cuadros; // representan cada cuadricula en la grilla

    public HistorialGrafico(){
        this.cuadros = new JLabel[5][16];
        configurarPanel();
        inicializarCeldas();
    }

    // crear una grilla de 5 filas y 16 columnas
    public void configurarPanel(){
        setLayout(new GridLayout(5, 16, 2, 2));
        setBackground(Color.DARK_GRAY);
    }

    public JLabel[][] getCuadros(){
        return cuadros;
    }

    // Dibuja el tablero inicialmente con los numeros del 1 al 75
    // y tambien las letras B I N G O
    public void inicializarCeldas() {
        // dibujar las letras en la primera columna
        String[] letras = {"B", "I", "N", "G", "0"};

        // recorrer cada fila y colocar le la letra en la columna 0 es decir la primera
        for (int i = 0; i < 5; i++) {
            // crear el jlabel para la letra y centrar lo
            JLabel cuadro = new JLabel(letras[i], SwingConstants.CENTER);
            cuadro.setOpaque(true);
            cuadro.setPreferredSize(new Dimension(30, 30)); // Tamaño fijo
            // colorear el jlabel de distinto color dependiendo la fila
            switch (i) {
                case 0:
                    // color azul claro
                    cuadro.setBackground(new Color(35, 201, 202));
                    break;
                case 1:
                    cuadro.setBackground(Color.RED);
                    break;
                case 2:
                    cuadro.setBackground(Color.GREEN);
                    break;
                case 3:
                    cuadro.setBackground(new Color(214, 194, 0));
                    break;
                case 4:
                    // purpura o mas o menos
                    cuadro.setBackground(new Color(190, 35, 189));
                    break;
                default:
                    break;
            }

            cuadro.setForeground(Color.WHITE);
            cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cuadro.setFont(new Font("Arial", Font.BOLD, 18));
            cuadros[i][0] = cuadro;
            add(cuadro);

            for (int j = 1; j <= 15; j++) { // Columnas de 1 a 15
                JLabel numero = new JLabel(String.valueOf((i * 15) + j), SwingConstants.CENTER);
                numero.setOpaque(true);
                numero.setFont(new Font("Arial", Font.BOLD, 16));
                numero.setPreferredSize(new Dimension(30, 30)); // Tamaño fijo
                numero.setBackground(new Color(20, 20, 20));
                numero.setForeground(new Color(150, 150, 150));
                numero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cuadros[i][j] = numero; // Guardar en la matriz lógica
                add(numero); // Agregar al panel
            }
        }
    }

    public abstract void marcarBola(int numero);

    public void colorearCuadro(int fila, int columna) {
        cuadros[fila][columna].setForeground(Color.YELLOW);
        validate();
        repaint();
    }
}
