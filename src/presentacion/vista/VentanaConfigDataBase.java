package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

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
    
    public VentanaConfigDataBase()
    {
        super();
        setTitle("Configuraci\u00F3n base de datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 602, 335);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 566, 326);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblUrl = new JLabel("URL");
        lblUrl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUrl.setBounds(85, 52, 180, 15);
        panel.add(lblUrl);

        txtUrl = new JTextField();
        txtUrl.setBounds(224, 51, 267, 19);
        panel.add(txtUrl);
        txtUrl.setColumns(10);

        JLabel lblPuerto = new JLabel("PUERTO");
        lblPuerto.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPuerto.setBounds(85, 78, 234, 15);
        panel.add(lblPuerto);

        txtPuerto = new JTextField();
        txtPuerto.setBounds(224, 77, 267, 19);
        panel.add(txtPuerto);
        txtPuerto.setColumns(10);

        JLabel lblUsuario = new JLabel("USUARIO");
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUsuario.setBounds(85, 107, 190, 15);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(224, 106, 267, 19);
        panel.add(txtUsuario);
        txtUsuario.setColumns(10);

        lblContrasea = new JLabel("CONTRASE\u00D1A");
        lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblContrasea.setBounds(85, 136, 180, 15);
        panel.add(lblContrasea);

        txtContrasena = new JTextField();
        txtContrasena.setColumns(10);
        txtContrasena.setBounds(224, 133, 267, 19);
        panel.add(txtContrasena);

        btnTest = new JButton("Probar conexi\u00F3n");
        btnTest.setBounds(224, 162, 267, 25);
        panel.add(btnTest);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(159, 243, 190, 25);
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