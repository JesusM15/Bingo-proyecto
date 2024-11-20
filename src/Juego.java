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
        this.ventanaJuego = new VentanaJuego(this);

        ventanaJuego.mostrar();
    }

    public Tombola getTombola() {
        return tombola;
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
}
