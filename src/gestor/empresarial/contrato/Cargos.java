package gestor.empresarial.contrato;

public enum Cargos {
    confianza("Empleado de confianza"),
    sindicalizado("Empleado sindicalizado"),
    temporal("Empleado temporal");
    //Metodos necesarios para obtener el nombre del cargo en String
    private final String nombre;

    Cargos(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
