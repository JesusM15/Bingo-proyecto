public class Carta {
    private int [][] numeros;

    public Carta() {
        numeros = new int[5][5];
    }

    public int[][] getNumeros() {
        return numeros;
    }

    public void setNumeros(int[][] numeros) {
        this.numeros = numeros;
    }

    public void setNumero(int n, int x, int y){
        this.numeros[x][y] = n;
    }

    public boolean verificarSiEsGanadora(Historial historial){
        for(int i = 0; i<this.numeros.length; i++){
            for(int j = 0; j < this.numeros[0].length; j++){
                if(this.numeros[i][j] != 0 && !historial.contains(this.numeros[i][j])){
                    return false;
                }
            }
        }
        return true;
    }

}