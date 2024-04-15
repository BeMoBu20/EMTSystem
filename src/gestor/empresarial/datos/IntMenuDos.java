package gestor.empresarial.datos;

import gestor.IntMenu;
import gestor.empresarial.empleados.Empleados;
import gestor.errores.GestionErrores;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntMenuDos extends JFrame{
    private JPanel panel1;
    private JTable tablaDP;
    private JTextField fieldID;
    private JTextField fieldName;
    private JTextField fieldMail;
    private JButton cerrarButton;
    private JButton agregarButton;
    private JTextField fieldWhats;
    private JButton borrarButton;
    private JButton modificarButton;
    private JPanel panelbase;
    private JScrollPane scrollForTable;
    private JTextPane datosGuardadosTextPane;
    private JTextPane datosPersonalesTextPane;
    DefaultTableModel mt = new DefaultTableModel(); //Creamos modelo de la tabla
    private Empleados empleados;
    private GestionErrores gestionErrores;

    public IntMenuDos(){
        empleados = empleados.getInstancia();
        gestionErrores = new GestionErrores();

        ajustesVentana(); //Ajustamos los parametros de nuestra ventana

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo de las funcionalidades de los botones
    }

    public void ajustesVentana(){
        setTitle("Menu EMT-System"); //Establecemos el titulo de la ventana
        this.setSize(1000,600); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1); //Obtenemos el contenido del panel
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
    }

    private void initComponents() {
        String ids[] = {"ID","Nombre Completo","Whatsapp","Email"};
        mt.setColumnIdentifiers(ids);
        tablaDP.getTableHeader().setResizingAllowed(false);
        tablaDP.getTableHeader().setReorderingAllowed(false);
        tablaDP.setModel(mt);
        if (empleados.datosPerVacios() == false) {
            actualizarTablaDesdeDatosPersonales();// Si los arreglos no están vacíos, muestra los datos en la tabla
        }
    }

    private void obtenerYGuardarDatosPersonales() {
        //Obtenemos los datos de los JTextField
        String nombre = fieldName.getText();
        String whatsapp = fieldWhats.getText();
        String email = fieldMail.getText();

        // Guardamos los datos en DatosPersonales
        DatosPersonales obj = new DatosPersonales(nombre,whatsapp,email);

        //Guardamos nuestro obj en Empleados
        empleados.addDatosPersonales(obj);
        empleados.imprimirDatos();
    }

    private void actualizarTablaDesdeDatosPersonales() {
        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < 100; i++) {
            DatosPersonales obj = empleados.getInfoPersonal(i);
            if(obj != null){
                int id = empleados.getID(i);
                String nombre = obj.getNombre();
                String whatsapp = obj.getWhatsapp();
                String correo = obj.getCorreo();
                mt.addRow(new Object[]{id,nombre,whatsapp,correo});
            }
        }
    }

    public boolean verificarCampos(){
        boolean camposCorrectos = true;
        String titulo;
        if (fieldID.getText().isEmpty() || fieldName.getText().isEmpty() || fieldWhats.getText().isEmpty() || fieldMail.getText().isEmpty()) {
            // Mostramos un mensaje de error indicando al usuario qué campos olvidó rellenar
            titulo = gestionErrores.getDescripcionTecnica(1);
            String mensaje = "Por favor, complete todos los campos:\n";
            if (fieldID.getText().isEmpty()) {
                mensaje += "- ID\n";
            }
            if (fieldName.getText().isEmpty()) {
                mensaje += "- Nombre\n";
            }
            if (fieldWhats.getText().isEmpty()) {
                mensaje += "- Whatsapp\n";
            }
            if (fieldMail.getText().isEmpty()) {
                mensaje += "- Email\n";
            }
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
            camposCorrectos = false;
        } else {
            boolean hayDuplicados = empleados.buscarDuplicadosP(Integer.parseInt(fieldID.getText()),fieldName.getText(),fieldWhats.getText(),fieldMail.getText());
            if (hayDuplicados) {
                titulo = gestionErrores.getDescripcionTecnica(6);
                JOptionPane.showMessageDialog(null, "Ya se ha guardado un empleado con esos mismos datos, favor de verificar la información", titulo, JOptionPane.ERROR_MESSAGE);
                camposCorrectos = false;
            }
        }
        return camposCorrectos;
    }

    public void funcionesBotones(){
        // Agregamos un ListSelectionListener a la JTable
        tablaDP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Con esto evitamos eventos de selección múltiple
                    int selectedRow = tablaDP.getSelectedRow();
                    if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                        // Obtenemos datos de la fila seleccionada
                        Object id = tablaDP.getValueAt(selectedRow, 0);
                        Object nombre = tablaDP.getValueAt(selectedRow, 1);
                        Object whatsapp = tablaDP.getValueAt(selectedRow, 2);
                        Object email = tablaDP.getValueAt(selectedRow, 3);

                        // Mostrar los datos en los JTextField
                        fieldID.setText(id.toString());
                        fieldName.setText(nombre.toString());
                        fieldWhats.setText(whatsapp.toString());
                        fieldMail.setText(email.toString());
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
                String idText = fieldID.getText();
                String nombre = fieldName.getText();
                String wh = fieldWhats.getText();
                String correo = fieldMail.getText();
                boolean camposCorrectos = verificarCampos();// Verificar que ningún campo esté vacío o duplicado
                if (camposCorrectos == true) {
                    // Si todos los campos están correctos, procedemos a agregar la fila a la tabla
                    obtenerYGuardarDatosPersonales();
                    actualizarTablaDesdeDatosPersonales();
                    // Limpiamos los JTextField después de agregar la fila
                    fieldID.setText("");
                    fieldName.setText("");
                    fieldWhats.setText("");
                    fieldMail.setText("");
                }
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaDP.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tablaDP.getValueAt(selectedRow, 0);
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados
                    mt.removeRow(selectedRow); // Eliminamos la fila en la tabla
                    empleados.borrarEmpleado(indice); // Eliminamos los datos correspondientes en Empleados
                } else {
                    String titulo = gestionErrores.getDescripcionTecnica(5);
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún candidato para borrar", titulo, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaDP.getSelectedRow();
                if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                    int id = (int) tablaDP.getValueAt(selectedRow, 0);
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados

                    // Obtenemos los datos modificados
                    String nombre = fieldName.getText();
                    String whats = fieldWhats.getText();
                    String correo = fieldMail.getText();

                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaDP.setValueAt(nombre, selectedRow, 1);
                    tablaDP.setValueAt(whats, selectedRow, 2);
                    tablaDP.setValueAt(correo, selectedRow, 3);

                    // Creamos un objeto con los datos actualizados
                    DatosPersonales obj = new DatosPersonales(nombre,whats,correo);

                    //Guardamos nuestro obj en Empleados
                    empleados.modificarEmpleado(indice,obj);
                }
                else {
                    String titulo = gestionErrores.getDescripcionTecnica(5);
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún candidato para modificar", titulo, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
