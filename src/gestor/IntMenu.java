package gestor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestor.IntLogin;
import gestor.empresarial.datos.*;
import gestor.empresarial.contrato.*;
import gestor.empresarial.empleados.Empleados;

public class IntMenu extends JFrame{
    private JPanel panel1;
    private JButton cerrarSesionButton;
    private JButton datosPersonalesButton;
    private JButton datosEmpresarialesButton;
    private JButton contratosButton;
    Empleados empleados;

    public IntMenu(){
        empleados = empleados.getInstancia();
        setTitle("Menu EMT-System"); //Establecemos el titulo de la ventana
        this.setSize(800,400); //Establecemos el tamaño de la ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana
        habilitarBotones();
        funcionesBotones();
    }

    public void habilitarBotones(){
        if(empleados.datosPerVacios() == true){
            datosEmpresarialesButton.setEnabled(false);
            contratosButton.setEnabled(false);
        } else if(empleados.datosEmpVacios()){
            contratosButton.setEnabled(false);
        }
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
                IntMenuTres obj = new IntMenuTres();
                dispose();
            }
        });

        contratosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IntContratos obj = new IntContratos();
                dispose();
            }
        });
    }
}
