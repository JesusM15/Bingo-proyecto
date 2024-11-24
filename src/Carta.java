import java.util.Arrays;

public class Carta {
    private int [][] numeros;

    public Carta() {
        numeros = new int[5][5];
    }

    public void setNumero(int n, int x, int y){
        this.numeros[x][y] = n;
    }

    // ya existe este numer
    public boolean yaExisteElNumero(int n){
        for(int i = 0; i<numeros.length; i++){
            for(int j = 0; j<numeros[i].length; j++){
                if(numeros[i][j] == n){
                    return true;
                }
            }
        }
        return false;
    }

    // verifica si la carta tiene numeros que ya salieron por ende gano
    public boolean verificarSiEsGanadora(Historial historial){
        for(int i = 0; i<this.numeros.length; i++){
            for(int j = 0; j < this.numeros[0].length; j++){
                if(i == 2 && j ==2) continue; // no revisar el espacio gratis dar lo por valido.

                if(this.numeros[i][j] != 0 && !historial.contains(this.numeros[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        String[] letras = {"B", "I", "N", "G", "O"};

        for(int i = 0; i <letras.length; i++){
            sb.append(String.format("%3s", letras[i]));
        }
        sb.append("\n");

        for(int i = 0; i<this.numeros.length; i++){
            for(int j = 0; j<this.numeros[0].length; j++){
                if(this.numeros[i][j] != 0){
                    sb.append(String.format("%2s", this.numeros[i][j]));
                }else {
                    if(i == 2 && j == 2){
                        sb.append(String.format("%3s", "F"));
                    }else{
                        sb.append(String.format("%3s", "*"));
                    }
                }

                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}