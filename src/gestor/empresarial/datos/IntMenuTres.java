package gestor.empresarial.datos;

import gestor.IntMenu;
import gestor.empresarial.empleados.Empleados;
import gestor.errores.GestionErrores;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntMenuTres extends JFrame{
    private JPanel panel1;
    private JTextField fieldTelefono;
    private JTextField fieldAdscripcion;
    private JTextField fieldExtension;
    private JTextField fieldPuesto;
    private JButton agregarButton;
    private JButton cerrarButton;
    private JTable tablaDE;
    private JScrollPane scrollForTable;
    private JPanel panel2;
    private JButton borrarButton;
    private JButton modificarButton;
    private JLabel idEmpleado;
    private JLabel nombreEmpleado;
    private JLabel whatsEmpleado;
    private JLabel correoEmpleado;
    private JTextField fieldBuscador;
    private JTextPane datosEmpresarialesTextPane;
    private JTextPane listaDeEmpleadosTextPane;
    DefaultTableModel mt = new DefaultTableModel(); //Creamos modelo de la tabla
    private Empleados empleados;
    private int indice = -1;
    private GestionErrores gestionErrores;


    public IntMenuTres(){
        empleados = empleados.getInstancia();
        gestionErrores = new GestionErrores();

        ajustesVentana(); //Ajustamos los parametros de la ventana

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo que define las funcionalidades de los botones
    }

    public void ajustesVentana(){
        setTitle("Menu EMT-System"); //Establecemos el titulo de la ventana
        this.setSize(1100,500); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
    }

    private void initComponents() {
        String encabezados[] = {"ID","Nombre Completo","Extension", "Telefono Exterior","Adscripcion","Puesto"};
        mt.setColumnIdentifiers(encabezados); //Definimos los titulos de las columnas
        tablaDE.getTableHeader().setResizingAllowed(false); //No permitimos que se cambie el tamaño de la ventana
        tablaDE.getTableHeader().setReorderingAllowed(false); //No permitimos que el usuario reordene las columnas de la tabla
        tablaDE.setModel(mt); //Establecemos el diseño de la tabla
        if (empleados.datosEmpVacios() == false) {
            actualizarTablaDesdeDatosEmpresariales();// Si los arreglos no están vacíos, muestra los datos en la tabla
        }
    }

    private void obtenerYGuardarDatosEmpresariales(int indice) {
        //Obtenemos los datos de los JTextField
        String telefono = fieldTelefono.getText();
        String extension = fieldExtension.getText();
        String adscripcion = fieldAdscripcion.getText();
        String puesto = fieldPuesto.getText();

        // Guardamos los datos en DatosEmpresariales
        DatosEmpresariales obj = new DatosEmpresariales(telefono, extension, adscripcion, puesto);

        //Guardamos nuestro obj en Empleados
        empleados.addDatosEmpresariales(indice,obj);
        this.indice = -1;
        empleados.imprimirDatos();
    }

    private void actualizarTablaDesdeDatosEmpresariales() {
        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < 100; i++) {
            DatosEmpresariales obj = empleados.getInfoEmpresarial(i);
            DatosPersonales obj2 = empleados.getInfoPersonal(i);
            if(obj != null){
                int id = empleados.getID(i);
                String nombre = obj2.getNombre();
                String telefono = obj.getTelefonoExterior();
                String extension = obj.getExtension();
                String adscripcion = obj.getAdscripcion();
                String puesto = obj.getPuesto();
                mt.addRow(new Object[]{id,nombre,extension,telefono,adscripcion,puesto});
            }
        }
    }

    public void funcionesBotones() {
        fieldBuscador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusqueda = fieldBuscador.getText();
                if (textoBusqueda != null){
                    // Buscando el ID ingresado en la lista de IDs en Empleados
                    int idBuscado = Integer.parseInt(textoBusqueda); // Convertir a entero el id
                    indice = empleados.findEmpleado(idBuscado);

                    // Verificando si se encontró el ID
                    if (indice != -1) {
                        DatosPersonales datosPersonales = empleados.getInfoPersonal(indice);

                        //Obteneniendo la información relacionada al ID (nombre, whatsapp, correo) en el arreglo de DatosPersonales
                        String nombre = datosPersonales.getNombre();
                        String whatsapp = datosPersonales.getWhatsapp();
                        String correo = datosPersonales.getCorreo();

                        // Mostrando la información en la ventana
                        idEmpleado.setText("ID: "+ idBuscado);
                        nombreEmpleado.setText("Nombre: " + nombre);
                        whatsEmpleado.setText("Whatsapp: " + whatsapp);
                        correoEmpleado.setText("Correo: " + correo);
                        fieldBuscador.setText("");
                    } else {
                        String mensaje = gestionErrores.getDescripcionTecnica(4);
                        JOptionPane.showMessageDialog(IntMenuTres.this, mensaje, "ID no valido", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    String titulo = gestionErrores.getDescripcionTecnica(1);
                    JOptionPane.showMessageDialog(IntMenuTres.this, "Campo de busqueda vacio", titulo, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        tablaDE.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar eventos de selección múltiple
                    int selectedRow = tablaDE.getSelectedRow();
                    if (selectedRow != -1) { // Verificar si se seleccionó una fila
                        // Obtener datos de la fila seleccionada
                        Object telefono = tablaDE.getValueAt(selectedRow, 3);
                        Object extension = tablaDE.getValueAt(selectedRow, 2);
                        Object adscripcion = tablaDE.getValueAt(selectedRow, 4);
                        Object puesto = tablaDE.getValueAt(selectedRow, 5);

                        // Mostrar los datos en los JTextField
                        fieldTelefono.setText(telefono.toString());
                        fieldExtension.setText(extension.toString());
                        fieldAdscripcion.setText(adscripcion.toString());
                        fieldPuesto.setText(puesto.toString());
                    }
                }
            }
        });



        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IntMenu obj = new IntMenu();
                dispose();
            }
        });

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indice != -1){
                    String telefonos = fieldTelefono.getText();
                    String extension = fieldExtension.getText();
                    String adscripcion = fieldAdscripcion.getText();
                    String puesto = fieldPuesto.getText();
                    // Verificar que ningún campo esté vacío
                    if (telefonos.isEmpty() || extension.isEmpty() || adscripcion.isEmpty() || puesto.isEmpty()) {
                        // Mostrar un mensaje de error indicando al usuario qué campo olvidó rellenar
                        String mensaje = gestionErrores.getDescripcionTecnica(1);
                        if (telefonos.isEmpty()) {
                            mensaje += "- Telefono\n";
                        }
                        if (extension.isEmpty()) {
                            mensaje += "- Extension\n";
                        }
                        if (adscripcion.isEmpty()) {
                            mensaje += "- Adscripcion\n";
                        }
                        if (puesto.isEmpty()) {
                            mensaje += "- Puesto\n";
                        }
                        JOptionPane.showMessageDialog(null, mensaje, "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Si todos los campos están llenos, procedemos a agregar la fila a la tabla
                        obtenerYGuardarDatosEmpresariales(indice);
                        actualizarTablaDesdeDatosEmpresariales();
                        // Limpiamos los JTextField después de agregar la fila
                        fieldTelefono.setText("");
                        fieldExtension.setText("");
                        fieldAdscripcion.setText("");
                        fieldPuesto.setText("");
                        idEmpleado.setText("");
                        nombreEmpleado.setText("");
                        whatsEmpleado.setText("");
                        correoEmpleado.setText("");
                    }
                }
                else{
                    String mensaje = gestionErrores.getDescripcionTecnica(5);
                    JOptionPane.showMessageDialog(null,mensaje, "Datos no agregados", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaDE.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tablaDE.getValueAt(selectedRow, 0); //Obtenemos el id en la fila seleccionada de la tabla
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados
                    mt.removeRow(selectedRow); // Eliminamos la fila en la tabla
                    empleados.borrarEmpleado(indice); // Eliminamos los datos correspondientes en Empleados
                } else {
                    String mensaje = gestionErrores.getDescripcionTecnica(5);
                    JOptionPane.showMessageDialog(null,mensaje, "No se puede borrar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaDE.getSelectedRow();
                if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                    int id = (int) tablaDE.getValueAt(selectedRow, 0);
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados

                    // Obtenemos los datos modificados
                    String telefono = fieldTelefono.getText();
                    String extension = fieldExtension.getText();
                    String adscripcion = fieldAdscripcion.getText();
                    String puesto = fieldPuesto.getText();

                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaDE.setValueAt(telefono, selectedRow, 3);
                    tablaDE.setValueAt(extension, selectedRow, 2);
                    tablaDE.setValueAt(adscripcion, selectedRow, 4);
                    tablaDE.setValueAt(puesto, selectedRow, 5);

                    // Creamos un objeto con los datos actualizados
                    DatosEmpresariales obj = new DatosEmpresariales(telefono,extension,adscripcion,puesto);

                    //Guardamos nuestro obj en Empleados
                    empleados.addDatosEmpresariales(indice,obj);
                } else {
                    String mensaje = gestionErrores.getDescripcionTecnica(5);
                    JOptionPane.showMessageDialog(null,mensaje, "No se puede modificar", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
