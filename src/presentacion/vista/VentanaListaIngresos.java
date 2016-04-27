package presentacion.vista;

import javax.swing.JFrame;

public class VentanaListaIngresos {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public VentanaListaIngresos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
