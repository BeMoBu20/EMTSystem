package gestor.errores;

import gestor.archivos.ArchivoTexto;

public class GestionErrores {
    private String descripcionTecnica;
    private MapaErrores mapaErrores;
    private ArchivoTexto archivoTexto;

    public GestionErrores() {
        mapaErrores = new MapaErrores();
        archivoTexto = new ArchivoTexto("ListadoErrores");
    }

    public String getDescripcionTecnica(int noError) {
        // Obtenemos el mensaje del error que corresponde a ese numero de error
        descripcionTecnica = mapaErrores.obtenerMensajeError(noError);

        // Escribimos el código y el mensaje de error en el archivo de texto
        escribirEnArchivo(noError, descripcionTecnica);
        return descripcionTecnica;
    }

    private void escribirEnArchivo(int noError, String descripcionTecnica) {
        archivoTexto.abrirModoEscritura();
        archivoTexto.escribir("Código de error: " + noError + "\t");
        archivoTexto.escribir("Mensaje de error: " + descripcionTecnica + "\n");
        archivoTexto.cerrar();
    }


}
