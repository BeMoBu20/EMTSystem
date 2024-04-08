package gestor.empresarial.empleados;
import gestor.errores.GestionErrores;
import gestor.empresarial.contrato.*;
import gestor.empresarial.datos.DatosPersonales;

public final class Empleados implements iEmpleados{
    private int i;
    public GestionErrores error;

    public Empleados(){

    }
    public void addDatosPersonales(String nombre, String correo, String whatsapp){

    }
    public void addContrato(int id, int noContrato, String horario,Cargos tipoCargo){

    }

    public int findEmpleado(int id){

        return id;
    }
    public String findEmpleado(String nombre){

        return null;
    }
    public void setWhatsApp(int i,String whatsapp){

    }
    private String datosPersonales(int i){

        return null;
    }
    public String getInfoEmpleado(int i){

        return null;
    }
    public String getInfoEmpleado(String nombre){

        return nombre;
    }
    public void setAdscripcion(int i,String adscripcion){

    }
    public void setTelefonoExtension(int i,String telefonoExterior){

    }
    public void setPuesto(int i,String puesto){

    }
    public void showDatosEmpleado() {

    }
    public void showContratosEmpleado(int i){

    }
    public int getAntiguedad(int annio) {
        return 0;
    }
    public void setCargo(Cargos tipoCargo){

    }
}
