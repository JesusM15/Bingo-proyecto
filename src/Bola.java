public class Bola {
    private int numero;
    private String columna;
    private boolean marcada;

    public Bola(int numero, String columna) {
        if(numero < 1 || numero > 75){
            throw new IllegalArgumentException("El número debe estar entre 1 y 75."); // arrojar un error
        }
        this.numero = numero;
        this.columna = columna;
        this.marcada = false;
    }

    public boolean isMarcada(){
        return marcada;
    }

    public void setMarcada(boolean marcada){
        this.marcada = marcada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return columna + numero;
    }
}
