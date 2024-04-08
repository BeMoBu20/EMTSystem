package gestor.empresarial.datos;

import gestor.IntMenu;
import gestor.empresarial.datos.DatosPersonales;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
    private JLabel titulo;
    private JPanel panelbase;
    private JScrollPane scrollForTable;
    DefaultTableModel mt = new DefaultTableModel(); //Creamos modelo de la tabla
    private DatosPersonales datosPersonales; //Generamos un objeto tipo DatosPersonales

    public IntMenuDos(){
        datosPersonales = DatosPersonales.getInstancia();

        //Ajustamos los parametros de nuestra ventana
        setTitle("Menu EMT-System"); //Establecemos el titulo de la ventana
        this.setSize(1000,600); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1); //Obtenemos el contenido del panel
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo de las funcionalidades de los botones
        actualizarTablaDesdeDatosPersonales(); //Codigo para obtener los de la tabla
    }

    private void initComponents() {
        String ids[] = {"ID","Nombre Completo","Whatsapp","Email"};
        mt.setColumnIdentifiers(ids);
        tablaDP.getTableHeader().setResizingAllowed(false);
        tablaDP.getTableHeader().setReorderingAllowed(false);
        tablaDP.setModel(mt);
    }

    private void obtenerYGuardarDatosPersonales() {
        int id = Integer.parseInt(fieldID.getText());
        String nombre = fieldName.getText();
        String whatsapp = fieldWhats.getText();
        String email = fieldMail.getText();

        // Guardamos los datos en DatosPersonales
        datosPersonales.addDatos(id, nombre, whatsapp, email);
        datosPersonales.imprimirDatos();
    }

    private void actualizarTablaDesdeDatosPersonales() {
        datosPersonales.imprimirDatos();
        //Obtenemos los datos de las listas en DatosPersonales
        List<Integer> ids = datosPersonales.getId();
        List<String> nombres = datosPersonales.getNombre();
        List<String> whatsapps = datosPersonales.getWhatsapp();
        List<String> emails = datosPersonales.getCorreo();

        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < ids.size(); i++) {
            mt.addRow(new Object[]{ids.get(i), nombres.get(i), whatsapps.get(i), emails.get(i)});
        }
    }

    public void funcionesBotones(){
        // Agregar un ListSelectionListener a la JTable
        tablaDP.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar eventos de selección múltiple
                    int selectedRow = tablaDP.getSelectedRow();
                    if (selectedRow != -1) { // Verificar si se seleccionó una fila
                        // Obtener datos de la fila seleccionada
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
                // Verificar que ningún campo esté vacío
                if (idText.isEmpty() || nombre.isEmpty() || wh.isEmpty() || correo.isEmpty()) {
                    // Mostrar un mensaje de error indicando al usuario qué campo olvidó rellenar
                    String mensaje = "Por favor, complete todos los campos:\n";
                    if (idText.isEmpty()) {
                        mensaje += "- ID\n";
                    }
                    if (nombre.isEmpty()) {
                        mensaje += "- Nombre\n";
                    }
                    if (wh.isEmpty()) {
                        mensaje += "- Whatsapp\n";
                    }
                    if (correo.isEmpty()) {
                        mensaje += "- Email\n";
                    }
                    JOptionPane.showMessageDialog(null, mensaje, "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Si todos los campos están llenos, procede a agregar la fila a la tabla
                    int id = Integer.parseInt(idText);
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
                    mt.removeRow(selectedRow); // Eliminamos la fila en la tabla

                    // Eliminamos los datos correspondientes en DatosPersonales
                    datosPersonales.getId().remove(selectedRow);
                    datosPersonales.getNombre().remove(selectedRow);
                    datosPersonales.getWhatsapp().remove(selectedRow);
                    datosPersonales.getCorreo().remove(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún candidato para borrar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaDP.getSelectedRow();
                if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                    // Obtenemos los datos modificados
                    int id = Integer.parseInt(fieldID.getText());
                    String nombre = fieldName.getText();
                    String wh = fieldWhats.getText();
                    String correo = fieldMail.getText();

                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaDP.setValueAt(id, selectedRow, 0);
                    tablaDP.setValueAt(nombre, selectedRow, 1);
                    tablaDP.setValueAt(wh, selectedRow, 2);
                    tablaDP.setValueAt(correo, selectedRow, 3);

                    // Actualizamos los datos en DatosPersonales
                    datosPersonales.getId().set(selectedRow, id);
                    datosPersonales.getNombre().set(selectedRow, nombre);
                    datosPersonales.getWhatsapp().set(selectedRow, wh);
                    datosPersonales.getCorreo().set(selectedRow, correo);
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún candidato para modificar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
