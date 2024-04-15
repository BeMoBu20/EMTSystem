package gestor.errores;

import java.util.HashMap;
import java.util.Map;

public class MapaErrores {
    private Map<Integer, String> errores;

    public MapaErrores() {
        errores = new HashMap<>();
        inicializarErrores();
    }

    private void inicializarErrores() {
        errores.put(1, "Campos de texto vacios, favor de rellenar todos los campos:");
        errores.put(2, "El usuario o contraseña son incorrectos, favor de ingresar datos validos");
        errores.put(3, "Campos de texto vacios, por favor ingrese su usuario y contraseña");
        errores.put(4, "ID no valido, este ID no existe");
        errores.put(5, "No se ha seleccionado algun empleado para modificar");
        errores.put(6, "Datos duplicados");
    }

    public String obtenerMensajeError(int codigoError) {
        return errores.get(codigoError);
    }
}
