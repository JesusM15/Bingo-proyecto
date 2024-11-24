import javax.swing.*;
import java.util.Arrays;

public class Juego {
    private VentanaConfiguracion ventanaConfiguracion;
    private VentanaJuego ventanaJuego;
    private Patron patronSeleccionado;
    private Tombola tombola;
    private Historial historial;

    public Juego() {
        this.ventanaConfiguracion = new VentanaConfiguracion(this);
        this.historial = new Historial();
    }

    public void jugar(){
        seleccionarPatron();
    }

    private void seleccionarPatron(){
        ventanaConfiguracion.mostrar();
    }

    // este es un action que se llama cuando se hace click en un Jbutton de la ventana de configuracion
    // pasa como parametro el propio patron seleccionado, oculta la ventana de configuracion pasa a la de juego
    // y crea la instancia de la tombola pasando el patron seleccionado para poder evitar mostrar
    // bolas que no puedan salir o sean invalidas para el patron.
    public void setPatronSeleccionado(Patron patronSeleccionado) {
        this.patronSeleccionado = patronSeleccionado;
        ventanaConfiguracion.ocultar();
        this.tombola = new Tombola(patronSeleccionado);
        this.tombola.generarBolas();
        this.ventanaJuego = new VentanaJuego(this);

        ventanaJuego.mostrar();
    }

    public Historial getHistorial() {
        return historial;
    }

    // Método para sacar una bola
    public void sacarBola() {
        if (tombola == null) {
            throw new IllegalStateException("Tómbola no inicializada");
        }

        Bola bola = tombola.obtenerBola();
        historial.marcarBola(bola.getNumero());
    }

    private Carta crearCarta(){
        Carta carta = new Carta();
        boolean[][] estructuraPatron = patronSeleccionado.getPatron();

        for(int i = 0; i < estructuraPatron.length; i++){
            for(int j = 0; j<estructuraPatron[i].length; j++){
                if(estructuraPatron[i][j]){
                    int numero = 0;

                    // espacio free
                    if(i == 2 && j == 2){
                        continue;
                    }

                    do{

                        // validar mediante crear carta y carta validara si el numero es valido en la columna.
                        String numeroString = JOptionPane.showInputDialog(null, "Ingrese el numero de su carta\n" +
                                "En la Fila: " + (i + 1) + " Columna: " + getColumna(j) + "\n" + carta);

                        // parsear el numero pedido a un entero ya que JOptionPane.showInputDialog, devuelve
                        // una string y la matriz funciona con numeros enteros.
                        if (numeroString != null && !numeroString.trim().isEmpty()) {
                            try {
                                numero = Integer.parseInt(numeroString.trim());
                            } catch (NumberFormatException e) {
                                // Manejo de error si la entrada no es un número válido
                                JOptionPane.showMessageDialog(null, "Por favor, ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        if(!numeroValidoEnColumna(numero, getColumna(j)) || carta.yaExisteElNumero(numero)){
                            if(!numeroValidoEnColumna(numero, getColumna(j))){
                                JOptionPane.showMessageDialog(null, "ERROR, INPUT NO VALIDO PARA LA COLUMNA, VUELVA A ESCRIBIR EL NUMERO");
                            }else{
                                JOptionPane.showMessageDialog(null, "ERROR, EL NUMERO " + numero + " Ya existe en la carta");
                            }
                        }

                    } while (!numeroValidoEnColumna(numero, getColumna(j)) || carta.yaExisteElNumero(numero));

                    carta.setNumero(numero, i, j);
                }
            }
        }

        return carta;
    }

    // devuelve una string con la columna formateada teniendo en cuenta columna 1 - 5
    private String getColumna(int numeroCol){
        switch (numeroCol){
            case 0: return "B";
            case 1: return "I";
            case 2: return "N";
            case 3: return "G";
            case 4: return "O";
            default: return "0";
        }
    }

    public void verificarCarta(){
        Carta carta = crearCarta();
        if(verificarSiGano(carta)){
            JOptionPane.showMessageDialog(null, "FELICIDADES GANASTE!\nCarta:\n" + carta);
        }else {
            JOptionPane.showMessageDialog(null, "Tu carta no es ganadora.\nCarta:\n" + carta);
        }
    }

    // recorre la matriz que representa la carta, en las posiciones donde se coloco algun numero
    // se revisa si ya salieron y si no es asi entonces no ha ganado
    // de lo contrario se sigue revisando y si todas pasan pues es bingo.
    public boolean verificarSiGano(Carta carta){
        return carta.verificarSiEsGanadora(historial);
    }

    // revisa si el numero pasado si puede ir en la columna que se planea colocar
    // siguiendo los rangos de las letras B I N G O
    public boolean numeroValidoEnColumna(int numero, String columna){
        if (numero >= 1 && numero <= 15) {
            return columna.equals("B");
        } else if (numero >= 16 && numero <= 30) {
            return columna.equals("I");
        } else if (numero >= 31 && numero <= 45) {
            return columna.equals("N");
        } else if (numero >= 46 && numero <= 60) {
            return columna.equals("G");
        } else if (numero >= 61 && numero <= 75) {
            return columna.equals("O");
        } else {
            return false; // Número fuera de rango
        }
    }
}
