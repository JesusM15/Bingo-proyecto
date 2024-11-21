public class Carta {
    private int [][] numeros;

    public Carta() {
        numeros = new int[5][5];
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<this.numeros.length; i++){
            for(int j = 0; j<this.numeros[0].length; j++){
                if(this.numeros[i][j] != 0){
                    sb.append(String.format("%2s", this.numeros[i][j]));
                }else {
                    sb.append(String.format("%3s", "*"));
                }

                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}