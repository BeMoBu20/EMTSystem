package gestor;

import gestor.errores.GestionErrores;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntLogin extends JFrame {
    private JPanel panel1;
    private JTextField usuarioTextField;
    private JPasswordField cPass;
    private JButton cerrarB;
    private JButton ingresarButton;
    private JLabel etUser;
    private JLabel etPass;
    private JLabel imagUser;
    private JPasswordField pass;
    private GestionErrores gestionErrores;

    public IntLogin(){
        gestionErrores = new GestionErrores();
        setTitle("EMT-System"); //Estabalecemos el titulo de la ventana
        this.setSize(300,300); //Establecemos el tamaño de la ventana
        this.setResizable(false);
        this.setLocationRelativeTo(null); //Establecemos la posicion inicial de la ventana en el centro
        this.getContentPane().add(panel1);
        this.setVisible(true); //Volvemos nuestra ventana visible
        setDefaultCloseOperation(EXIT_ON_CLOSE); //Indicamos que termine la ejecucion del programa al cerrar la ventana

        cerrarB.addActionListener(e -> System.exit(0));

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioTextField.getText();
                String contrasenia = pass.getText();
                if (!usuario.isEmpty() && !contrasenia.isEmpty()) {
                    if (usuario.equals("Tomas") && contrasenia.equals("talento")) {
                        IntMenu obj = new IntMenu();
                        dispose();
                    } else {
                        String mensaje = gestionErrores.getDescripcionTecnica(2);
                        JOptionPane.showMessageDialog(null, mensaje, "Datos no válidos", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    String mensaje = gestionErrores.getDescripcionTecnica(3);
                    JOptionPane.showMessageDialog(null, mensaje, "Campos vacíos", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
