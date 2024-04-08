package gestor.archivos;
import java.io.*;

public abstract class ControlArchivos {
    private String archivo;

    public ControlArchivos(){
        this.archivo = archivo;
    }
    public boolean crear(){
        File file = new File(archivo);
        try {
            if (file.exists()) {
                System.out.println("Error: El archivo ya existe.");
                return false;
            } else {
                if (file.createNewFile()) {
                    System.out.println("Archivo creado exitosamente.");
                    return true;
                } else {
                    System.out.println("Error: No se pudo crear el archivo.");
                    return false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public boolean eliminar(){
        File file = new File(archivo);
        try {
            if (file.exists()) {
                // Confirmación de eliminación
                // Cerrar y eliminar si es posible
                if (file.delete()) {
                    System.out.println("Archivo eliminado exitosamente.");
                    return true;
                } else {
                    System.out.println("Error: No se pudo eliminar el archivo.");
                    return false;
                }
            } else {
                System.out.println("Error: El archivo no existe.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public boolean cambiar(String archivo){
        ArchivoTexto obj = new ArchivoTexto(archivo);
        obj.abrirModoEscritura();
        return true;
    }
    public boolean mover(String archivo){
        File file = new File(archivo);
        try {
            String nuevaUbicacion = null;
            File nuevaUbicacionFile = new File(nuevaUbicacion);
            if (file.exists()) {
                if (file.renameTo(nuevaUbicacionFile)) {
                    System.out.println("Archivo movido exitosamente.");
                    return true;
                } else {
                    System.out.println("Error: No se pudo mover el archivo.");
                    return false;
                }
            } else {
                System.out.println("Error: El archivo no existe.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
