package gestor.archivos;
import java.io.*;

public final class ArchivoTexto extends ControlArchivos implements iFileText{
    private File file;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;
    private boolean archivoExiste;
    private boolean modoLectura;
    private boolean modoEscritura;
    public ArchivoTexto(String tituloArchivo){
        super();
        try{
            file = new File(tituloArchivo);
            if(!file.exists())
                file.createNewFile();
            this.archivoExiste=true;
            this.modoLectura=false;
            this.modoEscritura=false;
        }
        catch (Exception e){
            System.out.println("Error al intentar buscar el archivo");
            this.archivoExiste=false;
        }
    }

    public void abrirModoLectura(){
        if(archivoExiste==true){
            try{
                fr = new FileReader(this.file.getAbsoluteFile());
                br = new BufferedReader(this.fr);
                this.modoLectura=true;
                System.out.println("Archivo abierto en modo lectura");
            } catch (Exception e){
                System.out.println("Error: El archivo no se puede abrir en modo lectura");
            }
        }
    }

    public String leer(){
        if(archivoExiste==true) {
            try {
                return this.br.readLine();
            } catch (Exception e) {

            }
        }
        return null;
    }

    public void abrirModoEscritura(){
        if(archivoExiste==true){
            try{
                fw = new FileWriter(this.file.getAbsoluteFile(),true);
                bw = new BufferedWriter(this.fw);
                modoEscritura=true;
                System.out.println("Archivo abierto en modo escritura");
            } catch (Exception e){
                System.out.println("Error: El archivo no se puede abrir en modo escritura");
            }
        }
    }

    public void escribir(String texto){
        if (archivoExiste==true){
            try{
                this.bw.write(texto +"\n");
            } catch (Exception e){
                System.out.println("Error no se puede escribir informacion en el archivo");
            }
        }
    }

    public void cerrar(){
        if(modoEscritura==true){
            try{
                this.bw.close();
                this.fw.close();
            } catch (Exception e){ }
        }
        else if (modoLectura==true) {
            try {
                this.br.close();
                this.fr.close();
            } catch (Exception e) {
            }
        }
    }
}
