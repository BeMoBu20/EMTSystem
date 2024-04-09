package gestor.empresarial.empleados;
import gestor.errores.GestionErrores;
import gestor.empresarial.contrato.*;
import gestor.empresarial.datos.DatosPersonales;

import java.util.ArrayList;
import java.util.List;

public final class Empleados implements iEmpleados{
    private int i;
    public GestionErrores error;
    private static Empleados instancia = null; // Creamos una istancia de la propia clase para aplicar el patrón de diseño Singleton

    //Creamos listas para guardar los DATOS PERSONALES
    private List<Integer> id; //Lista de ids
    private List<String> nombre; //Lista de nombres
    private List<String> whatsapp; //Lista de numeros de whatsapp
    private List<String> correo; //Lista de correos electronicos

    public Empleados() { //Constructor
        // Aqui inicializamos las listas
        id = new ArrayList<>();
        nombre = new ArrayList<>();
        whatsapp = new ArrayList<>();
        correo = new ArrayList<>();
    }

    public static Empleados getInstancia() { //Instancia unica para mantener los datos guardados toda la ejecucion del programa
        if (instancia == null) {
            instancia = new Empleados();
        }
        return instancia;
    }

    //METODOS PARA AGREGAR LOS DATOS
    public void addDatosPersonales(int id, String nombre, String whatsapp, String email) { //Metodo para añadir los datos personales
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

    //Metodos get para accesar a los valores de las listas
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
