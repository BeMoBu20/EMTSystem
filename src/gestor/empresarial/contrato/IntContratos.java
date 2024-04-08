package gestor.empresarial.contrato;

import gestor.IntMenu;
import gestor.empresarial.datos.DatosEmpresariales;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class IntContratos extends JFrame{

    private JPanel panel1;
    private JTextField fieldContrato;
    private JTextField fieldAnnio;
    private JTextField fieldHorario;
    private JButton agregarButton;
    private JScrollPane scrollForTable;
    private JTable tablaC;
    private JTextField fieldBuscar;
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
    DefaultTableModel mt = new DefaultTableModel(); //Creamos modelo de la tabla
    private DatosEmpresariales datosEmpresariales; //Generamos un objeto tipo DatosEmpresariales
    private Contrato contrato; //Generamos un objeto tipo Contrato
    private int idEncontrado = 0;
    private String nombreEncontrado = "";


    public IntContratos(){
        datosEmpresariales = DatosEmpresariales.getInstancia();
        contrato = Contrato.getInstancia();

        //Ajustamos los parametros de la ventana
        setTitle("Menu EMT-System"); //Estabalecemos el titulo de la ventana
        this.setSize(1100,500); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana

        comboCargo.setModel(new DefaultComboBoxModel<>(Cargos.values()));// Establecemos el modelo del JComboBox utilizando los valores de la clase enum Cargos

        initComponents(); //Ajustes de la tabla
        funcionesBotones(); //Codigo que define las funcionalidades de los botones
        actualizarTablaDesdeContrato(); //Codigo para obtener los datos de la tabla
    }

    private void initComponents() {
        String encabezados[] = {"ID","Nombre Completo","No.Contrato", "Año","Horario","Tipo de Cargo"};
        mt.setColumnIdentifiers(encabezados);
        tablaC.getTableHeader().setResizingAllowed(false);
        tablaC.getTableHeader().setReorderingAllowed(false);
        tablaC.setModel(mt);
    }

    private void obtenerYGuardarContrato() {
        int noContrato = Integer.parseInt(fieldContrato.getText());
        int annio = Integer.parseInt(fieldAnnio.getText());
        String horario = fieldHorario.getText();
        Cargos tipocargo = (Cargos) comboCargo.getSelectedItem();
        // Guardamos los datos en DatosEmpresariales
        contrato.addDatos(idEncontrado, nombreEncontrado, noContrato, annio, horario, tipocargo);
        contrato.imprimirDatos();
        idEncontrado = -1;
        nombreEncontrado = "";
    }

    private void actualizarTablaDesdeContrato() {
        contrato.imprimirDatos();
        //Obtenemos los datos de las listas en Contrato
        List<Integer> ids = contrato.getId();
        List<String> nombres = contrato.getNombre();
        List<Integer> noContrato = contrato.getNoContrato();
        List<Integer> annio = contrato.getAnnio();
        List<String> horario = contrato.getHorario();
        List<String> tipoCargo = contrato.getTipoCargo();

        // Limpiamos la tabla antes de agregar los nuevos datos para evitar duplicados
        mt.setRowCount(0);

        // Agregamos los datos a la tabla
        for (int i = 0; i < ids.size(); i++) {
            mt.addRow(new Object[]{ids.get(i), nombres.get(i), noContrato.get(i), annio.get(i), horario.get(i), tipoCargo.get(i)});
        }
    }

    public void funcionesBotones() {
        // Agregar un ListSelectionListener a la JTable
        tablaC.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evitar eventos de selección múltiple
                    int selectedRow = tablaC.getSelectedRow();
                    if (selectedRow != -1) { // Verificar si se seleccionó una fila
                        // Obtener datos de la fila seleccionada
                        Object noContrato = tablaC.getValueAt(selectedRow, 3);
                        Object annio = tablaC.getValueAt(selectedRow, 2);
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

        fieldBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusqueda = fieldBuscar.getText();
                if (textoBusqueda != null){
                    // Buscando el ID ingresado en la lista de IDs en DatosPersonales
                    int idBuscado = Integer.parseInt(textoBusqueda); // Convertir a entero
                    int indice = datosEmpresariales.getIds().indexOf(idBuscado);

                    // Verificando si se encontró el ID
                    if (indice != -1) {
                        // Obteneniendo la información relacionada al ID (nombre, adscripcion, puesto) en la lista en DatosEmpresariales
                        idEncontrado = idBuscado;
                        nombreEncontrado = datosEmpresariales.getNombres().get(indice);
                        String adscripcion = datosEmpresariales.getAdscripcion().get(indice);
                        String puesto = datosEmpresariales.getPuesto().get(indice);

                        // Mostrando la información en la ventana
                        labeIId.setText("ID: "+ idEncontrado);
                        labelName.setText("Nombre: " + nombreEncontrado);
                        labelAdscripcion.setText("Adscripcion: " + adscripcion);
                        labelPuesto.setText("Puesto: " + puesto);
                        fieldBuscar.setText("");
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
                    mt.removeRow(selectedRow); //Eliminamos la fila en la tabla

                    // Eliminamos los datos correspondientes en Contrato
                    contrato.getId().remove(selectedRow);
                    contrato.getNombre().remove(selectedRow);
                    contrato.getNoContrato().remove(selectedRow);
                    contrato.getAnnio().remove(selectedRow);
                    contrato.getHorario().remove(selectedRow);
                    contrato.getTipoCargo().remove(selectedRow);
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
                    // Obtenemos los datos modificados
                    String noContrato = fieldContrato.getText();
                    String annio = fieldAnnio.getText();
                    String horario = fieldHorario.getText();
                    Cargos tipoCargo = (Cargos) comboCargo.getSelectedItem();


                    // Actualizamos los datos en la fila seleccionada de la JTable
                    tablaC.setValueAt(noContrato, selectedRow, 3);
                    tablaC.setValueAt(annio, selectedRow, 2);
                    tablaC.setValueAt(horario, selectedRow, 4);
                    tablaC.setValueAt(tipoCargo, selectedRow, 5);

                    // Actualizamos los datos en Contrato
                    contrato.getNoContrato().set(selectedRow, Integer.valueOf(noContrato));
                    contrato.getAnnio().set(selectedRow, Integer.valueOf(annio));
                    contrato.getHorario().set(selectedRow, horario);
                    contrato.getTipoCargo().set(selectedRow, String.valueOf(tipoCargo));
                }
            }
        });
    }
}
