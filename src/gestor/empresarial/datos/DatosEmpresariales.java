package gestor.empresarial.datos;

import java.util.ArrayList;
import java.util.List;

public final class DatosEmpresariales{
    private static DatosEmpresariales instancia = null;

    //Creamos listas para guardar nuestros datos
    private List<Integer> ids;
    private List<String> nombres;
    private List<String> extension;
    private List<String> telefonoExterior;
    private List<String> adscripcion;
    private List<String> puesto;

    public DatosEmpresariales(){
        //Inicializamos nuestras listas
        ids = new ArrayList<>();
        nombres = new ArrayList<>();
        extension = new ArrayList<>();
        telefonoExterior = new ArrayList<>();
        adscripcion = new ArrayList<>();
        puesto = new ArrayList<>();
    }

    public static DatosEmpresariales getInstancia() {
        if (instancia == null) {
            instancia = new DatosEmpresariales();
        }
        return instancia;
    }

    public void addDatos(int ids, String nombres,String extension, String telefonoExterior, String adscripcion, String puesto) {
        //Con esta clase agregamos los datos a las listas
        this.ids.add(ids);
        this.nombres.add(nombres);
        this.extension.add(extension);
        this.telefonoExterior.add(telefonoExterior);
        this.adscripcion.add(adscripcion);
        this.puesto.add(puesto);
    }

    public void imprimirDatos() {
        System.out.println("Datos guardados en DatosPersonales:");
        for (int i = 0; i < puesto.size(); i++) {
            System.out.println("Telefono exterior: " + telefonoExterior.get(i) + ", Adscripcion: " + adscripcion.get(i) + ", Puesto: " + puesto.get(i));
        }
    }

    public List<Integer> getIds() {
        return ids;
    }
    public List<String> getNombres() {
        return nombres;
    }
    public List<String> getExtension(){
        return extension;
    }
    public List<String> getTelefonoExterior() {
        return telefonoExterior;
    }
    public List<String> getAdscripcion() {
        return adscripcion;
    }
    public List<String> getPuesto() {
        return puesto;
    }
}
