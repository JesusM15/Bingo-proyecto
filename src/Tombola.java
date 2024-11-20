import java.util.ArrayList;
import java.util.Collections;

public class Tombola {
    private GeneradorBolasAleatorias generador;
    private ArrayList<Bola> bolas;
    private Patron patron;

    public Tombola(Patron patron) {
        this.generador = new GeneradorBolasAleatorias();
        this.bolas = new ArrayList<>();
        this.patron = patron;
        generarBolas();
    }

    public Bola obtenerBola(){
        if(bolas.isEmpty()){
            return null;
        }
        Bola bola = bolas.get(0);
        bolas.remove(0);
        return bola;
    }

    public void generarBolas(){
        ArrayList<Bola> todasLasBolas = generador.generarBolas(1, 75);

        boolean[][] estructuraPatron = patron.getPatron(); // obtener el patron en su forma matriz booleana

        for (Bola bola : todasLasBolas) {
            int numero = bola.getNumero();

            // Verificar si el número corresponde a una posición válida en el patrón
            if (esPosicionValida(numero, estructuraPatron)) {
                bolas.add(bola);
            }
        }

        Collections.shuffle(bolas);
    }

    private boolean esPosicionValida(int numero, boolean[][] estructuraPatron) {

        int columna;
        if (numero >= 1 && numero <= 15) {
            columna = 0; // Columna B
        } else if (numero >= 16 && numero <= 30) {
            columna = 1; // Columna I
        } else if (numero >= 31 && numero <= 45) {
            columna = 2; // Columna N
        } else if (numero >= 46 && numero <= 60) {
            columna = 3; // Columna G
        } else if (numero >= 61 && numero <= 75) {
            columna = 4; // Columna O
        } else {
            return false; // Número fuera de rango
        }

        // revisar si el rango de numeros de la columna del numero sirve para el patron
        for(int i = 0; i <5; i++){
            if(estructuraPatron[i][columna]){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Bola> getBolas() {
        return bolas;
    }

}
