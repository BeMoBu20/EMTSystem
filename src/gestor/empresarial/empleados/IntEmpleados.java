package gestor.empresarial.empleados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import gestor.IntMenu;

public class IntEmpleados extends JFrame{
    private JPanel panel1;
    private JTextField fieldID;
    private JTextField fieldNombre;
    private JTextPane datosDelEmpleadoTextPane;
    private JTextPane datosPersonalesTextPane;
    private JTextPane datosEmpresarialesTextPane;
    private JTextPane datosDeContratoTextPane;
    private JButton cerrarButton;
    private JLabel textNombre;
    private JLabel textWhatsapp;
    private JLabel textCorreo;
    private JLabel textTelefono;
    private JLabel textAdscripcion;
    private JLabel textPuesto;
    private JLabel textContrato;
    private JLabel textAntiguedad;
    private JLabel textHorario;
    private JLabel textCargo;
    private Empleados empleados;

    public IntEmpleados() {
        setTitle("EMT-System - Datos de Empleados"); //Establecemos el titulo de la ventana
        this.setSize(685,269); //Establecemos el tamaño de la ventana
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
        funcionesBotones();
    }

    public void funcionesBotones() {
        fieldID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int textoBusqueda = Integer.parseInt(fieldID.getText());
                if (textoBusqueda != -1) {
                    empleados.findEmpleado(textoBusqueda);
                } else {
                    // Mostrar un mensaje de error si esta vacio el campo
                    JOptionPane.showMessageDialog(IntEmpleados.this, "Campo de busqueda vacio", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fieldID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoBusqueda = fieldID.getText();
                if (textoBusqueda != null) {
                    empleados.findEmpleado(textoBusqueda);
                } else {
                    // Mostrar un mensaje de error si esta vacio el campo
                    JOptionPane.showMessageDialog(IntEmpleados.this, "Campo de busqueda vacio", "Error", JOptionPane.ERROR_MESSAGE);
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
    }
}
