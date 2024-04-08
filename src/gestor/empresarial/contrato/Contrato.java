package gestor.empresarial.contrato;

import java.util.ArrayList;
import java.util.List;

public final class Contrato {
    private static Contrato instancia = null;

    //Creamos listas para guardar nuestros datos
    private List<Integer> id;
    private List<String> nombre;
    private List<Integer> noContrato;
    private List<Integer> annio;
    private List<String> horario;
    private List<Cargos> tipoCargo;
    List<String> nombresCargos = new ArrayList<>();

    public Contrato(){
        //Inicializamos nuestras listas
        id = new ArrayList<>();
        nombre = new ArrayList<>();
        noContrato = new ArrayList<>();
        annio = new ArrayList<>();
        horario = new ArrayList<>();
        tipoCargo = new ArrayList<>();
    }

    public static Contrato getInstancia() {
        if (instancia == null) {
            instancia = new Contrato();
        }
        return instancia;
    }

    public void addDatos(int id, String nombre,int noContrato, int annio, String horario, Cargos tipoCargo) {
        //Con esta clase agregamos los datos a las listas
        this.id.add(id);
        this.nombre.add(nombre);
        this.noContrato.add(noContrato);
        this.annio.add(annio);
        this.horario.add(horario);
        this.tipoCargo.add(tipoCargo);
    }

    public void imprimirDatos() {
        System.out.println("Datos guardados en Contrato:");
        for (int i = 0; i < id.size(); i++) {
            System.out.println("id: " + id.get(i) + ", Nombre: " + nombre.get(i) + ", noContrato: " + noContrato.get(i));
        }
    }

    public List<Integer> getId() {
        return id;
    }

    public List<String> getNombre() {
        return nombre;
    }

    public List<Integer> getNoContrato() {
        return noContrato;
    }

    public List<Integer> getAnnio() {
        return annio;
    }

    public List<String> getHorario() {
        return horario;
    }

    public List<String> getTipoCargo() {
        for (Cargos cargo : tipoCargo) {
            nombresCargos.add(cargo.toString());
        }
        return nombresCargos;
    }
}
