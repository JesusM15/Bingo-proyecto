import java.util.ArrayList;

public class GeneradorBolasAleatorias implements GeneradorBolas{

    @Override
    public ArrayList<Bola> generarBolas(int rangoInicio, int rangoFin) {
        ArrayList<Bola> bolas = new ArrayList<>();
        String columna; int numero;

        for(int i = rangoInicio; i <= rangoFin; i++) {
            numero = i;
            columna = determinarColumna(numero);
            bolas.add(new Bola(numero, columna));
        }

        return bolas;
    }

    private String determinarColumna(int numero) {
        if (numero >= 1 && numero <= 15) {
            return "B";
        } else if (numero >= 16 && numero <= 30) {
            return "I";
        } else if (numero >= 31 && numero <= 45) {
            return "N";
        } else if (numero >= 46 && numero <= 60) {
            return "G";
        } else if (numero >= 61 && numero <= 75) {
            return "O";
        }
        return "";
    }
}
