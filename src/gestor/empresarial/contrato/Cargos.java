package gestor.empresarial.contrato;

public enum Cargos {
    confianza("Empleado de confianza"),
    sindicalizado("Empleado sindicalizado"),
    temporal("Empleado temporal");

    private final String nombre;

    Cargos(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
