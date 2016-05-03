package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaConfigDataBase extends javax.swing.JFrame
{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUrl;
    private JTextField txtPuerto;
    private JTextField txtUsuario;
    private JLabel lblContrasea;
    private JTextField txtContrasena;
    private JButton btnTest;
    private JButton btnAceptar;
    /**
     * Creates new form ConfigDataBase
     */
    
    public VentanaConfigDataBase()
    {
        super();
        setTitle("Configuracion de Base de Datos");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 279);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(12, 12, 438, 239);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUrl = new JLabel("Url o Direccion");
        lblUrl.setBounds(12, 12, 127, 15);
        panel.add(lblUrl);

        txtUrl = new JTextField();
        txtUrl.setText("localhost");
        txtUrl.setBounds(149, 10, 200, 19);
        panel.add(txtUrl);
        txtUrl.setColumns(10);

        JLabel lblPuerto = new JLabel("Puerto");
        lblPuerto.setBounds(12, 58, 70, 15);
        panel.add(lblPuerto);

        txtPuerto = new JTextField();
        txtPuerto.setText("3306");
        txtPuerto.setBounds(149, 56, 114, 19);
        panel.add(txtPuerto);
        txtPuerto.setColumns(10);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(12, 99, 70, 15);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(149, 87, 166, 19);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        lblContrasea = new JLabel("Contraseña");
        lblContrasea.setBounds(12, 128, 108, 15);
        panel.add(lblContrasea);

        txtContrasena = new JTextField();
        txtContrasena.setColumns(10);
        txtContrasena.setBounds(149, 126, 166, 19);
        panel.add(txtContrasena);

        btnTest = new JButton("Test");
        btnTest.setBounds(22, 185, 117, 25);
        panel.add(btnTest);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(176, 185, 117, 25);
        panel.add(btnAceptar);
    }
    
    public JTextField getTxtUrl() {
        return txtUrl;
    }

    public JTextField getTxtPuerto() {
        return txtPuerto;
    }



    public JTextField getTxtUsuario() {
        return txtUsuario;
    }



    public JButton getBtnTest() {
        return btnTest;
    }



    public JButton getBtnAceptar() {
        return btnAceptar;
    }



    public JTextField getTxtContrasena() {
        return txtContrasena;
    }

}