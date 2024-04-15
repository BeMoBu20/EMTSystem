package gestor.empresarial.contrato;

import gestor.IntMenu;
import gestor.empresarial.datos.DatosEmpresariales;
import gestor.empresarial.datos.DatosPersonales;
import gestor.empresarial.empleados.Empleados;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntContratos extends JFrame{

    private JPanel panel1;
    private JTextField fieldContrato;
    private JTextField fieldAnnio;
    private JTextField fieldHorario;
    private JButton agregarButton;
    private JScrollPane scrollForTable;
    private JTable tablaC;
    private JTextField fieldBuscador;
    private JComboBox comboCargo;
    private JButton borrarButton;
    private JButton modificarButton;
    private JLabel labeIId;
    private JLabel labelName;
    private JButton cerrarButton;
    private JLabel labelAdscripcion;
    private JLabel labelPuesto;
    private JTextPane contratosTextPane;
    private JTextPane listaDeEmpleadosTextPane;
    private JTextPane empleadoTextPane;
    private JTextPane empresaTextPane;
    DefaultTableModel mt = new DefaultTableModel(); //Creamos modelo de la tabla
    private Empleados empleados;
    private int indice = -1;


    public IntContratos(){
        empleados = empleados.getInstancia();

        ajustesVentana(); //Ajustamos los parametros de la ventana

        comboCargo.setModel(new DefaultComboBoxModel<>(Cargos.values()));// Establecemos el modelo del JComboBox utilizando los valores de la clase enum Cargos

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo que define las funcionalidades de los botones
    }

    private void ajustesVentana(){
        setTitle("Menu EMT-System"); //Establecemos el titulo de la ventana
        this.setSize(1100,500); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
    }

    private void initComponents() {
        String encabezados[] = {"ID","Nombre Completo","No.Contrato", "Año","Horario","Tipo de Cargo"};
        mt.setColumnIdentifiers(encabezados); //Definimos los titulos de las columnas
        tablaC.getTableHeader().setResizingAllowed(false); //No permitimos que se cambie el tamaño de la ventana
        tablaC.getTableHeader().setReorderingAllowed(false); //No permitimos que el usuario reordene las columnas de la tabla
        tablaC.setModel(mt); //Establecemos el diseño de la tabla
        if (empleados.datosContratoVacios() == false) {
            actualizarTablaDesdeContrato();// Si los arreglos no están vacíos, muestra los datos en la tabla
        }
    }

    private void obtenerYGuardarContrato() {
        //Obtenemos los datos de los JTextField
        int noContrato = Integer.parseInt(fieldContrato.getText());
        int annio = Integer.parseInt(fieldAnnio.getText());
        String horario = fieldHorario.getText();
        Cargos tipocargo = (Cargos) comboCargo.getSelectedItem();

        //Guardamos los datos en Contrato
        Contrato obj = new Contrato(noContrato, annio, horario, tipocargo);

        //Guardamos nuestro obj en Empleados
        empleados.addContrato(indice,obj);
        empleados.imprimirDatos();
    }

    private void actualizarTablaDesdeContrato() {
        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < 100; i++) {
            Contrato obj = empleados.getInfoContrato(i);
            DatosPersonales obj2 = empleados.getInfoPersonal(i);
            if(obj != null){
                int id = empleados.getID(i);
                String nombre = obj2.getNombre();
                int noContrato = obj.getNoContrato();
                int annio = obj.getAnnio();
                String horario = obj.getHorario();
                Cargos cargo = obj.getTipoCargo();
                mt.addRow(new Object[]{id,nombre,noContrato,annio,horario,cargo});
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
                    int idBuscado = Integer.parseInt(textoBusqueda); // Convertir a entero
                    indice = empleados.findEmpleado(idBuscado);

                    // Verificando si se encontró el ID
                    if (indice != -1) {
                        DatosPersonales datosPersonales = empleados.getInfoPersonal(indice);
                        DatosEmpresariales datosEmpresariales = empleados.getInfoEmpresarial(indice);

                        // Obteneniendo la información relacionada al ID (nombre, adscripcion, puesto) en la lista en DatosEmpresariales
                        String nombre= datosPersonales.getNombre();
                        String adscripcion = datosEmpresariales.getAdscripcion();
                        String puesto = datosEmpresariales.getPuesto();

                        // Mostrando la información en la ventana
                        labeIId.setText("ID: "+ idBuscado);
                        labelName.setText("Nombre: " + nombre);
                        labelAdscripcion.setText("Adscripcion: " + adscripcion);
                        labelPuesto.setText("Puesto: " + puesto);
                        fieldBuscador.setText("");
                    } else {
                        // Mostrar un mensaje de error si no se encuentra el ID
                        JOptionPane.showMessageDialog(IntContratos.this, "ID no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    // Mostrar un mensaje de error si esta vacio el campo
                    JOptionPane.showMessageDialog(IntContratos.this, "Campo de busqueda vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Agregar un ListSelectionListener a la JTable
        tablaC.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar eventos de selección múltiple
                    int selectedRow = tablaC.getSelectedRow();
                    if (selectedRow != -1) { // Verificar si se seleccionó una fila
                        // Obtener datos de la fila seleccionada
                        Object noContrato = tablaC.getValueAt(selectedRow, 2);
                        Object annio = tablaC.getValueAt(selectedRow, 3);
                        Object horario = tablaC.getValueAt(selectedRow, 4);
                        Object tipoCargo = tablaC.getValueAt(selectedRow, 5);

                        // Mostrar los datos en los JTextField
                        fieldContrato.setText(noContrato.toString());
                        fieldAnnio.setText(annio.toString());
                        fieldHorario.setText(horario.toString());
                        comboCargo.setSelectedItem(tipoCargo);
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
                    String noContrato = fieldContrato.getText();
                    String annio = fieldAnnio.getText();
                    String horario = fieldHorario.getText();
                    Cargos tipoCargo = (Cargos) comboCargo.getSelectedItem();
                    // Verificar que ningún campo esté vacío
                    if (noContrato.isEmpty() || annio.isEmpty() || horario.isEmpty() || tipoCargo == null) {
                        // Mostrar un mensaje de error indicando al usuario qué campo olvidó rellenar
                        String mensaje = "Por favor, complete todos los campos:\n";
                        if (noContrato.isEmpty()) {
                            mensaje += "- No.Contrato\n";
                        }
                        if (annio.isEmpty()) {
                            mensaje += "- Año\n";
                        }
                        if (horario.isEmpty()) {
                            mensaje += "- Horario\n";
                        }
                        if (tipoCargo == null) {
                            mensaje += "- Tipo de Cargo\n";
                        }
                        JOptionPane.showMessageDialog(null, mensaje, "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Si todos los campos están llenos, procede a agregar la fila a la tabla
                        obtenerYGuardarContrato();
                        actualizarTablaDesdeContrato();
                        // Limpiamos los JTextField después de agregar la fila
                        fieldContrato.setText("");
                        fieldAnnio.setText("");
                        fieldHorario.setText("");
                        comboCargo.setSelectedItem(null);
                        labeIId.setText("");
                        labelName.setText("");
                        labelAdscripcion.setText("");
                        labelPuesto.setText("");
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
                int selectedRow = tablaC.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tablaC.getValueAt(selectedRow, 0); //Obtenemos el id en la fila seleccionada de la tabla
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados
                    mt.removeRow(selectedRow); // Eliminamos la fila en la tabla
                    empleados.borrarEmpleado(indice); // Eliminamos los datos correspondientes en Empleados
                } else {
                    JOptionPane.showMessageDialog(null,"No se ha seleccionado a ningun empleado para borrar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaC.getSelectedRow();
                if (selectedRow != -1) { // Verificamos si se seleccionó una fila
                    int id = (int) tablaC.getValueAt(selectedRow,0);
                    int indice = empleados.findEmpleado(id); //Buscamos al empleado en la lista de Empleados

                    // Obtenemos los datos modificados
                    int noContrato = Integer.parseInt(fieldContrato.getText());
                    int annio = Integer.parseInt(fieldAnnio.getText());
                    String horario = fieldHorario.getText();
                    Cargos tipoCargo = (Cargos) comboCargo.getSelectedItem();


                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaC.setValueAt(noContrato, selectedRow, 2);
                    tablaC.setValueAt(annio, selectedRow, 3);
                    tablaC.setValueAt(horario, selectedRow, 4);
                    tablaC.setValueAt(tipoCargo, selectedRow, 5);

                    //Creamos un objeto con los datos actualizados
                    Contrato obj = new Contrato(noContrato,annio,horario,tipoCargo);

                    //Guardamos nuestro obj en Empleados
                    empleados.addContrato(indice,obj);
                }
            }
        });
    }
}
