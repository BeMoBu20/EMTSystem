package gestor.archivos;

interface iFileText {
    public void abrirModoLectura();
    public String leer();
    public void abrirModoEscritura();
    public void escribir(String a);
    public void cerrar();
}
