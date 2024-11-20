import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Historial extends HistorialGrafico {
    private Bola[][] celdas; // Matriz lógica de bolas

    public Historial() {
        super();
        celdas = new Bola[5][15]; // 5 filas (B, I, N, G, O) y 15 columnas (1-75)
        crearCeldas();
    }

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

        // obtiene la columna segun la fila en la que este y dependiendo el numero
        columna = (numero - fila*15);

        this.celdas[fila][columna - 1].setMarcada(true);
        super.colorearCuadro(fila, columna);
        super.repaint();
        super.revalidate();
    }

    // Inicializa la matriz con las bolas y sus números correspondientes
    private void crearCeldas() {
        int numero = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                String columna = determinarColumna(i);
                celdas[i][j] = new Bola(numero++, columna);
            }
        }
    }


    // Retorna la columna asociada con la fila (B, I, N, G, O)
    private String determinarColumna(int fila) {
        switch (fila) {
            case 0: return "B";
            case 1: return "I";
            case 2: return "N";
            case 3: return "G";
            case 4: return "O";
            default: return "";
        }
    }

    // Marca una bola lógica como seleccionada
    public void marcarBola(int x, int y) {
        celdas[x][y].setMarcada(true); // Marcar la bola como seleccionada
    }

    // Retorna la matriz de bolas
    public Bola[][] getCeldas() {
        return celdas;
    }

    // Resetea todo el historial lógico
    public void reset() {
        inicializarCeldas(); // Vuelve a inicializar todas las celdas
    }
}
