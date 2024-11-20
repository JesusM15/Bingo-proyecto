import javax.swing.*;
import java.awt.*;

public class HistorialGrafico extends JPanel{
    private Historial historial;
    private JLabel[][] cuadros; // representan cada cuadricula en la grilla

    public HistorialGrafico(Historial historial){
        this.historial = historial; // Usar el historial proporcionado
        this.cuadros = new JLabel[5][16];
        configurarPanel();
        inicializarCeldas();
    }

    // crear una grilla de 5 filas y 16 columnas
    public void configurarPanel(){
        setLayout(new GridLayout(5, 16, 2, 2));
        setBackground(Color.DARK_GRAY);
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
                    cuadro.setBackground(Color.YELLOW);
                    break;
                case 4:
                    // purpura o mas o menos
                    cuadro.setBackground(new Color(202, 35, 189));
                    break;
                default:
                    break;
            }

            cuadro.setForeground(Color.BLACK);
            cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            cuadros[i][0] = cuadro;
            add(cuadro);
        }

        // recorre las bolas del historial para dibujar las
        Bola[][] celdas = historial.getCeldas();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                JLabel numero = new JLabel(String.valueOf(celdas[i][j].getNumero()), SwingConstants.CENTER);
                numero.setOpaque(true);
                numero.setBackground(Color.LIGHT_GRAY);
                numero.setForeground(Color.BLACK);
                numero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cuadros[i][j + 1] = numero; // +1 porque la columna 0 tiene las letras
                add(numero);
            }
        }
    }

    // conforme un numero pasado al estar en orden calcula su posicion y lo pinta.
    public void marcarBola(int numero){
        int fila = 0, columna = 0;
        if(numero >= 16 && numero <= 30){
            fila = 1;
        }else if(numero >= 31 && numero <= 45){
            fila = 2;
        }else if(numero >= 46 && numero <= 60){
            fila = 3;
        }else if(numero >= 61 && numero <= 75){
            fila = 4;
        }

        columna = (numero - 1) % 15 + 1; // Restamos 1 al número porque las columnas empiezan desde 1

        JLabel cuadro = cuadros[fila][columna];
        cuadro.setBackground(Color.YELLOW); // Cambiar color
        cuadro.setForeground(Color.BLACK);
        historial.marcarBola(fila, columna);
    }

    public void resetHistorial() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                JLabel cuadro = cuadros[i][j];
                cuadro.setBackground(Color.LIGHT_GRAY);
                cuadro.setForeground(Color.BLACK);
            }
        }
    }

}
