package gestor.empresarial.empresa;
import gestor.empresarial.empleados.*;
import gestor.errores.GestionErrores;

public final class Empresa {
    private String nombreEmpresa;
    private String representanteLegal;
    private String telefono;
    private String rfc;
    public Empleados datosRH;
    public GestionErrores error;

    public Empresa(String nombreEmpresa,String representanteLegal){
        this.nombreEmpresa = nombreEmpresa;
        this.representanteLegal = representanteLegal;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }
    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getInfo() {
        System.out.println("Nombre de la empresa: "+ nombreEmpresa);
        System.out.println("Representante legal: "+ representanteLegal);
        System.out.println("Telefono: "+ telefono);
        return null;
    }
}
