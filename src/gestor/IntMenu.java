package gestor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestor.IntLogin;
import gestor.empresarial.datos.*;
import gestor.empresarial.contrato.*;

public class IntMenu extends JFrame{
    private JPanel panel1;
    private JButton cerrarSesionButton;
    private JButton datosPersonalesButton;
    private JButton datosEmpresarialesButton;
    private JButton contratosButton;
    private DatosPersonales datosPersonales;
    private DatosEmpresariales datosEmpresariales;
    private Contrato contrato;

    public IntMenu(){
        datosEmpresariales = DatosEmpresariales.getInstancia();
        datosPersonales = DatosPersonales.getInstancia();
        setTitle("Menu EMT-System"); //Estabalecemos el titulo de la ventana
        this.setSize(800,400); //Establecemos el tamaño de la ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
        funcionesBotones();
    }

    private boolean datosPersonalesNoVacios() {
        return !datosPersonales.getId().isEmpty(); // Verificamos si ya hay informacion en DatosPersonales
    }

    private boolean datosEmpresarialesNoVacios() {
        return !datosEmpresariales.getIds().isEmpty(); // Verificamos si ya hay informacion en DatosEmpresariales
    }

    private boolean contratosNoVacios() {
        //return !contrato.getId().isEmpty(); //Verificamos si hay informacion en contratos
        return false;
    }

    public void funcionesBotones(){

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IntLogin obj = new IntLogin();
                dispose();
            }
        });

        datosPersonalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IntMenuDos obj = new IntMenuDos();
                dispose();
            }
        });

        datosEmpresarialesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datosPersonalesNoVacios()) {
                    IntMenuTres obj = new IntMenuTres();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Aun no se han añadido datos personales", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        contratosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datosPersonalesNoVacios()) {
                    IntContratos obj = new IntContratos();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Aun no se han añadido datos en Datos Empresariales", "Campos Vacíos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
