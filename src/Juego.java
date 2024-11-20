public class Juego {
    private VentanaConfiguracion ventanaConfiguracion;
    private Patron patronSeleccionado;
    private Tombola tombola;

    public Juego() {
        this.ventanaConfiguracion = new VentanaConfiguracion(this);
    }

    public void jugar(){
        seleccionarPatron();
    }

    private void seleccionarPatron(){
        ventanaConfiguracion.setVisible(true);
    }

    public void setPatronSeleccionado(Patron patronSeleccionado) {
        this.patronSeleccionado = patronSeleccionado;
        ventanaConfiguracion.setVisible(false);
        this.tombola = new Tombola(patronSeleccionado);
    }
}
