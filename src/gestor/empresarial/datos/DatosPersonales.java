package gestor.empresarial.datos;

import java.util.ArrayList;
import java.util.List;

public class DatosPersonales {
    private String nombre;
    private String whatsapp;
    private String correo;

    public DatosPersonales(String nombre, String whatsapp, String correo) {
        this.nombre = nombre;
        this.whatsapp = whatsapp;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getWhatsapp() {
        return whatsapp;
    }


    public String getCorreo() {
        return correo;
    }
}
