package gestor.empresarial.empleados;
import gestor.empresarial.datos.*;
import gestor.errores.GestionErrores;
import gestor.empresarial.contrato.*;
import java.time.Year;

public final class Empleados implements iEmpleados{
    private int i=0;
    public GestionErrores error;
    private static Empleados instancia = null; // Creamos una istancia de la propia clase para aplicar el patrón de diseño Singleton
    private int[] ids = new int[100];
    private DatosPersonales[] datosP = new DatosPersonales[100];
    private DatosEmpresariales[] datosE = new DatosEmpresariales[100];
    private Contrato[] datosC = new Contrato[100];

    public Empleados() { //Constructor

    }
    public static Empleados getInstancia() { //Instancia unica para mantener los datos guardados toda la ejecucion del programa
        if (instancia == null) {
            instancia = new Empleados();
        }
        return instancia;
    }

    public void imprimirDatos(){
        if (datosPerVacios() == false){
            for(int j=0; j<100; j++){
                if (datosP[j] != null){
                    String nombre = datosP[j].getNombre();
                    String correo = datosP[j].getCorreo();
                    String whatsapp = datosP[j].getWhatsapp();
                    System.out.println("ID:" + ids[j] + "\tNombre:" + nombre + "\tCorreo:" + correo + "\tWhats:" + whatsapp);
                }
            }
        }

        if (datosEmpVacios() == false){
            for(int j=0; j<100; j++){
                if (datosE[j] != null){
                    String telefono = datosE[j].getTelefonoExterior();
                    String extencion = datosE[j].getExtension();
                    String adscripcion = datosE[j].getAdscripcion();
                    String puesto = datosE[j].getPuesto();
                    System.out.println("ID:" + ids[j] + "\tTelefono:" + telefono + "\tExtension:" + extencion + "\tAdscripcion:" + adscripcion + "\tPuesto:" + puesto);
                }
            }
        }

        if (datosContratoVacios() == false){
            for(int j=0; j<100; j++){
                if (datosC[j] != null){
                    int noContrato = datosC[j].getNoContrato();
                    int annio = datosC[j].getAnnio();
                    String horario = datosC[j].getHorario();
                    Cargos tipoCargo = datosC[j].getTipoCargo();
                    System.out.println("ID:" + ids[j] + "\tNoConctato:" + noContrato + "\tAnnio:" + annio + "\tHorario:" + horario + "\tCargo:" + tipoCargo);
                }
            }
        }
    }

    //METODOS PARA AGREGAR LOS DATOS
    public void addDatosPersonales(DatosPersonales datosPersonales){
        this.datosP[this.i] = datosPersonales;
        ids[i]=i+1;
        this.i++;
    }

    public void addDatosEmpresariales(int indice, DatosEmpresariales datosEmpresariales){
        this.datosE[indice] = datosEmpresariales;
    }

    public void addContrato(int indice, Contrato contrato){
        this.datosC[indice] = contrato;
    }

    //METODOS PARA BUSCAR A UN EMPLEADO

    public int findEmpleado(int id){
        int indice = -1;
        for (int j = 0; j < 100; j++){
            if (ids[j] == id){
                indice = j; // Si se encuentra el ID se actualizamos el indice
                break;
            }
        }
        return indice;
    }
    public int findEmpleado(String nombre){
        int indice = -1;
        for (int j = 0; j < 100; j++){
            if (datosP[j] != null && datosP[j].getNombre().equals(nombre)){
                indice = j; // Si se encuentra el ID actualizamos el indice
                break;
            }
        }
        return indice;
    }

    //METODOS PARA RETORNAR LA INFORMACION DE UN EMPLEADO
    public int getID(int indice){
        return ids[indice];
    }
    public DatosPersonales getInfoPersonal(int indice){
        DatosPersonales datosPersonales = this.datosP[indice];
        return datosPersonales;
    }

    public DatosEmpresariales getInfoEmpresarial(int indice){
        DatosEmpresariales datosEmpresariales = this.datosE[indice];
        return datosEmpresariales;
    }

    public Contrato getInfoContrato(int indice){
        Contrato contrato = this.datosC[indice];
        return contrato;
    }

    @Override
    public Object getInfoEmpleado(int a) {
        return null;
    }

    @Override
    public String getInfoEmpleado(String b) {
        return null;
    }

    //METODOS PARA COMPROBAR SI LOS ARREGLOS ESTAN VACIOS

    public boolean datosPerVacios(){
        int suma=0;
        boolean vacio;
        for(int j=0; j<100; j++){
            if(datosP[j] != null){
                suma += 1;
            }
        }
        if (suma>0){
            vacio = false;
        }else{
            vacio = true;
        }
        return vacio;
    }

    public boolean datosEmpVacios(){
        int suma=0;
        boolean vacio;
        for(int j=0; j<100; j++){
            if(datosE[j] != null){
                suma += 1;
            }
        }
        if (suma>0){
            vacio = false;
        }else{
            vacio = true;
        }
        return vacio;
    }

    public boolean datosContratoVacios(){
        int suma=0;
        boolean vacio;
        for(int j=0; j<100; j++){
            if(datosC[j] != null){
                suma += 1;
            }
        }
        if (suma>0){
            vacio = false;
        }else{
            vacio = true;
        }
        return vacio;
    }

    public void showDatosEmpleado() {

    }
    public void showContratosEmpleado(int i){

    }
    public int getAntiguedad(int annio) {
        int antiguedad = Year.now().getValue() - annio;
        return 0;
    }

    //METODO PARA BORRAR A UN EMPLEADO
    public void borrarEmpleado(int indice){
        ids[indice] = -1;
        datosP[indice] = null;
        datosE[indice] = null;
        datosC[indice] = null;
    }

    //METODO PARA MODIFICAR UN EMPLEADO
    public void modificarEmpleado(int indice, DatosPersonales datosPersonales){
        this.datosP[indice] = datosPersonales;
    }

    //METODO PARA BUSCAR DATOS DUPLICADOS
    public boolean buscarDuplicadosP(int id, String nombre, String whatsapp, String correo) {
        boolean hayDuplicados = false;
        for (int j = 0; j < 100; j++) {
            DatosPersonales obj = getInfoPersonal(j);
            if (obj != null) {
                int idP = ids[j];
                String nombreP = obj.getNombre();
                String whatsP = obj.getWhatsapp();
                String correoP = obj.getCorreo();
                if (idP == id || nombreP.equals(nombre) || whatsP.equals(whatsapp) || correoP.equals(correo)) {
                    hayDuplicados = true;
                    break;
                }
            }
        }
        return hayDuplicados;
    }
}
