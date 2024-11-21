import java.awt.*;

public class Patron {
    private String nombre;
    private boolean[][] patron;

    public Patron(String nombre, boolean[][] patron) {
        this.nombre = nombre;
        this.patron = patron;
    }

    public String getNombre() {
        return nombre;
    }


    public boolean[][] getPatron() {
        return patron;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<patron.length; i++) {
            for(int j = 0; j<patron[i].length; j++) {
                if(patron[i][j]) {
                    sb.append('1');
                }else{
                    sb.append('0');
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public void dibujar(Graphics g, int x, int y, int size) {
        for (int i = 0; i < patron.length; i++) {
            for (int j = 0; j < patron[i].length; j++) {
                if (patron[i][j]) {
                    g.setColor(Color.BLUE); // Celdas llenas (activadas)
                    g.fillRect(x + j * size, y + i * size, size, size);
                } else {
                    g.setColor(Color.WHITE); // Fondo de las celdas vacías
                    g.fillRect(x + j * size, y + i * size, size, size);
                }
                // Dibujar bordes de las celdas
                g.setColor(Color.BLACK);
                g.drawRect(x + j * size, y + i * size, size, size);
            }
        }
    }
}
