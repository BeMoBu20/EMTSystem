package gestor.errores;

public class GestionErrores {
    private boolean existeError;
    private int noError;
    private String DescripcionTecnica;

    public GestionErrores(int noError, String descripcionTecnica){
        this.noError = noError;
    }
    private void cargarErrores(){

    }

    public void setNoError(int noError) {
        this.noError = noError;
    }

    public int getNoError() {
        return noError;
    }

    public String getDescripcionTecnica() {
        return DescripcionTecnica;
    }

    public boolean existeError(){
        return false;
    }
}
