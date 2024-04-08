package gestor.empresarial.datos;

import java.util.ArrayList;
import java.util.List;

public class DatosPersonales {
    private static DatosPersonales instancia = null;

    //En las listas es posible guardar n cantidad de datos, al ser una tabla es mas conveniente
    private List<Integer> id; //Lista de ids
    private List<String> nombre; //Lista de nombres
    private List<String> whatsapp; //Lista de numeros de whatsapp
    private List<String> correo; //Lista de correos electronicos

    public DatosPersonales() {
        // Aqui inicializamos las listas
        id = new ArrayList<>();
        nombre = new ArrayList<>();
        whatsapp = new ArrayList<>();
        correo = new ArrayList<>();
    }

    public static DatosPersonales getInstancia() {
        if (instancia == null) {
            instancia = new DatosPersonales();
        }
        return instancia;
    }

    public void addDatos(int id, String nombre, String whatsapp, String email) {
        //Con esta clase agregamos los datos a las listas
        this.id.add(id);
        this.nombre.add(nombre);
        this.whatsapp.add(whatsapp);
        this.correo.add(email);
    }

    public void imprimirDatos() {
        System.out.println("Datos guardados en DatosPersonales:");
        for (int i = 0; i < id.size(); i++) {
            System.out.println("ID: " + id.get(i) + ", Nombre: " + nombre.get(i) + ", WhatsApp: " + whatsapp.get(i) + ", Email: " + correo.get(i));
        }
    }

    //Metodos de acceso a los valores de las listas
    public List<Integer> getId() {
        return id;
    }

    public List<String> getNombre() {
        return nombre;
    }

    public List<String> getCorreo() {
        return correo;
    }

    public List<String> getWhatsapp() {
        return whatsapp;
    }
}
