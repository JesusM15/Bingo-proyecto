import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class GestorDePatrones {
    private ArrayList<Patron> patronesDisponibles;

    public GestorDePatrones() {
        patronesDisponibles = new ArrayList<>();
        inicializarPatrones();
    }

    // generar los 38 patrones de manera automatizada aprovechando los similares
    private void inicializarPatrones() {
        // generar 5-row
        generarPatrones5InARow();

        generarPatrones6Pack();

        // generar manualmente los de 8

        //diamond
        boolean[][] diamond = {
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, false, false, true},
                {false, true, false, true, false},
                {false, false, true, false, false}
        };
        patronesDisponibles.add(
                new Patron(
                        "Diamond",
                        diamond
                )
        );

        // small center box
        boolean[][] smallCenterBox = {
                {false, false, false, false, false},
                {false, true, true, true, false},
                {false, true, false, true, false},
                {false, true, true, true, false},
                {false, false, false, false, false},
        };

        patronesDisponibles.add(
                new Patron(
                        "Small-Center-Box",
                        smallCenterBox
                )
        );

    }

    private void generarPatrones5InARow(){
        // patrones horizontales.
        for(int row = 0; row<5; row++){
            boolean[][] patronHorizontal = new boolean[5][5];
            for(int col = 0; col<5; col++){
                patronHorizontal[row][col] = true;
            }
            patronesDisponibles.add(
                    new Patron(
                            "5-in-a-Row-Horizontal-" + (row + 1),
                            patronHorizontal
                    )
            );
        }

        // patrones verticales
        for(int col = 0; col<5; col++){
            boolean[][] patronVertical = new boolean[5][5];
            for(int row = 0; row<5; row++){
                patronVertical[row][col] = true;
            }
//            System.out.println(Arrays.deepToString(patronVertical));
            patronesDisponibles.add(new Patron(
                    ("5-in-a-Row-Vertical-" + (col + 1)),
                    patronVertical));
        }

        // patrones diagonales

        boolean[][] diagonalDescendente = new boolean[5][5];
        for(int row = 0; row<5; row++){
            diagonalDescendente[row][row] = true;
        }
        patronesDisponibles.add(new Patron(
                "5-in-a-Row-Diagonal-des",
                diagonalDescendente
        ));

        boolean[][] diagonalAscendente = new boolean[5][5];
        for(int row = 0; row<5; row++){
            diagonalAscendente[4 - row][row] = true;
        }
        patronesDisponibles.add(new Patron(
                "5-in-a-Row-Diagonal-asc",
                diagonalAscendente
        ));

    }

    private void generarPatrones6Pack(){
        int width = 2, height = 3;
        int counter = 0;

        /* son para los 6 en forma vertical
           basicamente este recorre cada fila inicial y columna inicial las cuales representan el punto de partida
           desde el cual crecera 1 columna al lado y 2 de altura desde ese punto 2x3
        * */
        for(int rowInicial = 0; rowInicial < 3; rowInicial++){

            for(int colInicial = 0; colInicial<=3; colInicial++){
                boolean[][] patronHorizontal = new boolean[5][5];

                for(int col = colInicial; col < (colInicial + width); col++){
                    for(int row = rowInicial; row < (height + rowInicial); row++){
                        patronHorizontal[row][col] = true;
                    }
                }

                patronesDisponibles.add(new Patron(
                        "Patron-6-Pack-Vertical-" + counter + "-" + rowInicial + "-" + colInicial,
                        patronHorizontal
                ));
                counter++;
            }

        }

        // Ancho y altura invertidos para patrones horizontales
        width = 3;
        height = 2;
        counter = 0;

        for (int rowInicial = 0; rowInicial <= 3; rowInicial++) { // Máximo fila inicial = 3 (para caber 2 filas más)
            for (int colInicial = 0; colInicial < 3; colInicial++) { // Máximo columna inicial = 3 (para caber 3 columnas más)
                boolean[][] patronHorizontal = new boolean[5][5];

                for (int row = rowInicial; row < (rowInicial + height); row++) {
                    for (int col = colInicial; col < (colInicial + width); col++) {
                        patronHorizontal[row][col] = true;
                    }
                }

                patronesDisponibles.add(new Patron(
                        "Patron-6-Pack-Horizontal-" + counter + "-" + rowInicial + "-" + colInicial,
                        patronHorizontal
                ));

                counter++;
            }
        }

    }

    public ArrayList<Patron> getPatronesDisponibles() {
        return patronesDisponibles;
    }

    public Patron seleccionarPatron(int indice) {
        if (indice >= 0 && indice < patronesDisponibles.size()) {
            return patronesDisponibles.get(indice);
        }
        return null;
    }
}
