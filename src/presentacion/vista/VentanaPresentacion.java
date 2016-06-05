package presentacion.vista;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dto.UsuarioDTO;
import presentacion.controlador.ControladorVentanaPrincipal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class VentanaPresentacion extends Thread {

	private JFrame frame;
	private int retardoInicial = 1000;
	private ControladorVentanaPrincipal controladorVentanaPrincipal;

	@Override
	public void run() {
		try {

			sleep(retardoInicial);

			controladorVentanaPrincipal.iniciar();
			frame.setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public VentanaPresentacion(UsuarioDTO user) {
		initialize();
		controladorVentanaPrincipal = new ControladorVentanaPrincipal(new VentanaPrincipal(), user);

		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();

		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 981, 523);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPresentacion.class.getResource("/logo.png")));
		frame.getContentPane().setLayout(null);

		JLabel lbl_LOGO = new JLabel("");
		lbl_LOGO.setIcon(new ImageIcon(VentanaPresentacion.class.getResource("/logo.png")));
		lbl_LOGO.setBounds(145, 0, 623, 388);
		frame.getContentPane().add(lbl_LOGO);

		JLabel labelBienvenida = new JLabel(
				"<html> <br><br><b><i>Bienvenido</i></b><br><H3> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp Versión 1.0</H3></html>");
		labelBienvenida.setBounds(new Rectangle(272, 244, 460, 157));
		labelBienvenida.setForeground(Color.decode("#848484"));
		labelBienvenida.setFont(new Font("Tahoma", Font.PLAIN, 35));
		labelBienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelBienvenida);

		JLabel lbl_Loading = new JLabel("");
		lbl_Loading.setBounds(new Rectangle(398, 382, 210, 148));
		lbl_Loading.setIcon(new ImageIcon(VentanaPresentacion.class.getResource("/loading.gif")));
		frame.getContentPane().add(lbl_Loading);

	}

}
