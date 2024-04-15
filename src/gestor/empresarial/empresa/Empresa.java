package gestor.empresarial.empresa;
import gestor.empresarial.empleados.*;
import gestor.errores.GestionErrores;

public final class Empresa {
    private String nombreEmpresa = "Itera Process";
    private String representanteLegal = "Ariel Súcari";
    private String telefono = "(+52) (55) 3300 0650";
    private String rfc = "IPM181023H34";

    public Empresa(){

    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRfc() {
        return rfc;
    }
}
