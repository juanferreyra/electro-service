package presentacion.vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class BackUpVista extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnRestaurar;
	private JButton btnCrearCopia;

	public BackUpVista() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("BackUp");
		setBounds(100, 100, 384, 136);
		getContentPane().setLayout(null);
		btnRestaurar = new JButton("Restaurar");
		btnRestaurar.setBounds(206, 31, 146, 34);
		btnRestaurar.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().add(btnRestaurar);
		btnCrearCopia = new JButton("Crear Copia ");
		btnCrearCopia.setBounds(24, 32, 157, 33);
		btnCrearCopia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearCopia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(btnCrearCopia);
	}

	public JButton getBtnRestaurar() {
		return btnRestaurar;
	}

	public JButton getBtnCrearCopia() {
		return btnCrearCopia;
	}
	
	
}

