package gestor.empresarial.datos;

import gestor.IntMenu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IntMenuTres extends JFrame{
    private JPanel panel1;
    private JTextField fieldTelefono;
    private JTextField fieldAdscripcion;
    private JTextField fieldExtension;
    private JTextField fieldPuesto;
    private JButton agregarButton;
    private JButton cerrarButton;
    private JTable tablaEmpleados;
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
    private DatosEmpresariales datosEmpresariales; //Generamos un objeto tipo DatosPersonales
    private DatosPersonales datosPersonales; //Generamos un objeto tipo DatosPersonales
    private int idEncontrado = 0;
    private String nombreEncontrado = "";


    public IntMenuTres(){
        datosEmpresariales = DatosEmpresariales.getInstancia();
        datosPersonales = DatosPersonales.getInstancia();

        //Ajustamos los parametros de la ventana
        setTitle("Menu EMT-System"); //Estabalecemos el titulo de la ventana
        this.setSize(1100,500); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo que define las funcionalidades de los botones
        actualizarTablaDesdeDatosEmpresariales(); //Codigo para obtener los datos de la tabla
    }

    private void initComponents() {
        String ids[] = {"ID","Nombre Completo","Extension", "Telefono Exterior","Adscripcion","Puesto"};
        mt.setColumnIdentifiers(ids);
        tablaEmpleados.getTableHeader().setResizingAllowed(false);
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        tablaEmpleados.setModel(mt);
    }

    private void obtenerYGuardarDatosEmpresariales() {
        String telefono = fieldTelefono.getText();
        String extension = fieldExtension.getText();
        String adscripcion = fieldAdscripcion.getText();
        String puesto = fieldPuesto.getText();

        // Guardamos los datos en DatosEmpresariales
        datosEmpresariales.addDatos(idEncontrado, nombreEncontrado, extension, telefono, adscripcion, puesto);
        datosEmpresariales.imprimirDatos();
        idEncontrado = -1;
        nombreEncontrado = "";
    }

    private void actualizarTablaDesdeDatosEmpresariales() {
        datosEmpresariales.imprimirDatos();
        //Obtenemos los datos de las listas en DatosEmpresariales
        List<Integer> ids = datosEmpresariales.getIds();
        List<String> nombres = datosEmpresariales.getNombres();
        List<String> extensiones = datosEmpresariales.getExtension();
        List<String> telefonos = datosEmpresariales.getTelefonoExterior();
        List<String> adscripciones = datosEmpresariales.getAdscripcion();
        List<String> puesto = datosEmpresariales.getPuesto();

        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < telefonos.size(); i++) {
            mt.addRow(new Object[]{ids.get(i), nombres.get(i), extensiones.get(i), telefonos.get(i), adscripciones.get(i), puesto.get(i)});
        }
    }

    public void funcionesBotones() {
        // Agregar un ListSelectionListener a la JTable
        tablaEmpleados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar eventos de selección múltiple
                    int selectedRow = tablaEmpleados.getSelectedRow();
                    if (selectedRow != -1) { // Verificar si se seleccionó una fila
                        // Obtener datos de la fila seleccionada
                        Object telefono = tablaEmpleados.getValueAt(selectedRow, 3);
                        Object extension = tablaEmpleados.getValueAt(selectedRow, 2);
                        Object adscripcion = tablaEmpleados.getValueAt(selectedRow, 4);
                        Object puesto = tablaEmpleados.getValueAt(selectedRow, 5);

                        // Mostrar los datos en los JTextField
                        fieldTelefono.setText(telefono.toString());
                        fieldExtension.setText(extension.toString());
                        fieldAdscripcion.setText(adscripcion.toString());
                        fieldPuesto.setText(puesto.toString());
                    }
                }
            }
        });

        fieldBuscador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusqueda = fieldBuscador.getText();
                if (textoBusqueda != null){
                    // Buscando el ID ingresado en la lista de IDs en DatosPersonales
                    int idBuscado = Integer.parseInt(textoBusqueda); // Convertir a entero
                    int indice = datosPersonales.getId().indexOf(idBuscado);

                    // Verificando si se encontró el ID
                    if (indice != -1) {
                        // Obteneniendo la información relacionada al ID (nombre, whatsapp, correo) en la lista en DatosPersonales
                        idEncontrado = idBuscado;
                        nombreEncontrado = datosPersonales.getNombre().get(indice);
                        String whatsapp = datosPersonales.getWhatsapp().get(indice);
                        String correo = datosPersonales.getCorreo().get(indice);

                        // Mostrando la información en la ventana
                        idEmpleado.setText("ID: "+ idBuscado);
                        nombreEmpleado.setText("Nombre: " + nombreEncontrado);
                        whatsEmpleado.setText("Whatsapp: " + whatsapp);
                        correoEmpleado.setText("Correo: " + correo);
                        fieldBuscador.setText("");
                    } else {
                        // Mostrar un mensaje de error si no se encuentra el ID
                        JOptionPane.showMessageDialog(IntMenuTres.this, "ID no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    // Mostrar un mensaje de error si esta vacio el campo
                    JOptionPane.showMessageDialog(IntMenuTres.this, "Campo de busqueda vacio", "Error", JOptionPane.ERROR_MESSAGE);
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
                if (idEncontrado != -1 && nombreEncontrado != ""){
                    String telefonos = fieldTelefono.getText();
                    String extension = fieldExtension.getText();
                    String adscripcion = fieldAdscripcion.getText();
                    String puesto = fieldPuesto.getText();
                    // Verificar que ningún campo esté vacío
                    if (telefonos.isEmpty() || extension.isEmpty() || adscripcion.isEmpty() || puesto.isEmpty()) {
                        // Mostrar un mensaje de error indicando al usuario qué campo olvidó rellenar
                        String mensaje = "Por favor, complete todos los campos:\n";
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
                        // Si todos los campos están llenos, procede a agregar la fila a la tabla
                        obtenerYGuardarDatosEmpresariales();
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
                    JOptionPane.showMessageDialog(null,"No se ha seleccionado a ningun empleado para añadir los datos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaEmpleados.getSelectedRow();
                if (selectedRow != -1) {
                    mt.removeRow(selectedRow); //Eliminamos la fila en la tabla

                    // Eliminamos los datos correspondientes en DatosPersonales
                    datosEmpresariales.getIds().remove(selectedRow);
                    datosEmpresariales.getNombres().remove(selectedRow);
                    datosEmpresariales.getExtension().remove(selectedRow);
                    datosEmpresariales.getTelefonoExterior().remove(selectedRow);
                    datosEmpresariales.getAdscripcion().remove(selectedRow);
                    datosEmpresariales.getPuesto().remove(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null,"No se ha seleccionado a ningun empleado para borrar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaEmpleados.getSelectedRow();
                if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                    // Obtenemos los datos modificados
                    String telefono = fieldTelefono.getText();
                    String extension = fieldExtension.getText();
                    String adscripcion = fieldAdscripcion.getText();
                    String puesto = fieldPuesto.getText();

                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaEmpleados.setValueAt(telefono, selectedRow, 3);
                    tablaEmpleados.setValueAt(extension, selectedRow, 2);
                    tablaEmpleados.setValueAt(adscripcion, selectedRow, 4);
                    tablaEmpleados.setValueAt(puesto, selectedRow, 5);

                    // Actualizamos los datos en DatosEmpresarial
                    datosEmpresariales.getExtension().set(selectedRow, extension);
                    datosEmpresariales.getTelefonoExterior().set(selectedRow, telefono);
                    datosEmpresariales.getAdscripcion().set(selectedRow, adscripcion);
                    datosEmpresariales.getPuesto().set(selectedRow, puesto);
                }
            }
        });
    }
}
