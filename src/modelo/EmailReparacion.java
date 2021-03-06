package modelo;

import java.net.Socket;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

import dto.UsuarioDTO;
import presentacion.vista.VentanaReparacion;

public class EmailReparacion extends Thread {

	private Ingreso ingreso;
	private UsuarioDTO usuario;
	private VentanaReparacion ventana;
	private Reparacion reparacion;

	public EmailReparacion(Ingreso ingreso, UsuarioDTO usuario, VentanaReparacion ventana, Reparacion reparacion) {

		this.ingreso = ingreso;
		this.usuario = usuario;
		this.ventana = ventana;
		this.setReparacion(reparacion);

	}

	@Override
	public void run() {

		this.enviarReparacion();
	}

	private void enviarReparacion() {

		String destinatario = ingreso.getCliente().getMail();
		String estado = "";
		String envio = "";
		float montoTotal = 0;

		if (!ventana.getBtnReparado().isVisible()) {
			estado = "Reparado";
		} else {
			estado = "Irreparable";
		}

		if (estado.equals("Reparado")) {

			if (ingreso.getIngreso().getEnvio()) {

				montoTotal = Float.parseFloat(ventana.getLblMontoEnvio().getText())
						+ Float.parseFloat(ventana.getLblMontoPresupuestado().getText());
			} else {
				montoTotal = Float.parseFloat(ventana.getLblMontoEnvio().getText());
			}
		}

		if (ingreso.getIngreso().getEnvio()) {

			if (!ingreso.getIngreso().getEnvio_default()) {

				envio = " coordinar el envio del equipo al domicilio: " + ingreso.getCliente().getDireccion()
						+ ". Muchas Gracias ";
			} else {
				envio = " coordinar el envio del equipo al domicilio: "
						+ ingreso.getIngreso().getDireccion_alternativa() + ". Muchas Gracias ";
			}

		} else {
			envio = " coordinar fecha y horario en el que puede pasar a retirar el equipo por la siguiente direccion: "
					+ "  Darregueyra 3896,Los Polvorines, Malvinas Argentinas, Buenos Aires. Muchas Gracias";
		}

		// asunto
		String asunto = "Aviso de reparacion  de " + ingreso.ingr.getDescripcion() + " de Electro Service.";

		/// Cuerpo de mensaje
		String mensaje =

				"<p>Estimado cliente:  " + ingreso.getCliente().getNombre() + " " + ingreso.getCliente().getApellido()
						+ " </p><br>" + "<p style=text-indent:4cm > La reparaci&#243;n del producto <b>"
						+ ingreso.ingr.getDescripcion() + "</b> " + " se encuentra en estado : &nbsp;<b>" + estado
						+ "</b> " + ". Recuerde que el monto total a abonar es: $ &nbsp;" + montoTotal + " </p><br> "
						+ "<p> Le solicitamos que por favor se comunique al tel&#233;fono : <b>4685 -5438</b>, para <b>"
						+ envio + "</b></p><br>" + "<p> <b>ATTE:  </b>" + usuario.getNombre() + "   "
						+ usuario.getApellido() + "</p>";

		if (this.verificarConeccion()) {

			this.enviar(destinatario, mensaje, asunto);
		} else {

			JOptionPane.showMessageDialog(null, "No se ha detectado una red y el mail no ha podido enviarse.");
		}

	}

	private boolean verificarConeccion() {

		String dirWeb = "www.google.com.ar";
		int puerto = 80;

		try {
			@SuppressWarnings("resource")
			Socket prueba = new Socket(dirWeb, puerto);

			if (prueba.isConnected()) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	private void enviar(String destinatarios, String mensaje, String asunto) {
		try {
			// se obtiene el objeto Session. La configuración es para
			// una cuenta de gmail.
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "reparaciones.electroservice@gmail.com");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props, null);
			// session.setDebug(true);

			// Se compone la parte del texto
			BodyPart texto = new MimeBodyPart();

			texto.setContent("<font color=red> <h1 align=center>Electro R  S.R.L.</font></h1></font> <br>"
					+ "<p align=center>Darregueyra 3896,Los Polvorines, Malvinas Argentinas, Buenos Aires<br></p>"
					+ "<font color=Navy><h3 align=center>Tel&#233;fono: 4685 -5438  </h3></font> <br>" + mensaje,
					"text/html");

			// Se compone el adjunto con la imagen
			@SuppressWarnings("unused")
			BodyPart adjunto = new MimeBodyPart();
			// adjunto.setDataHandler(
			// new DataHandler(new FileDataSource("PresentacionInicial.pdf")));
			// adjunto.setFileName("Presentacion Inicial.pdf");

			// Una MultiParte para agrupar texto e imagen.
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			// multiParte.addBodyPart(adjunto);

			// Se compone el correo, dando to, from, subject y el
			// contenido.
			MimeMessage message = new MimeMessage(session);

			// de
			message.setFrom(new InternetAddress("reparaciones.electroservice@gmail.com"));

			// para
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));

			message.setSubject(asunto);
			message.setContent(multiParte);

			// Se envia el correo.
			Transport t = session.getTransport("smtp");
			t.connect("reparaciones.electroservice@gmail.com", "ungs2016");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

			// Con esta imprimimos en consola que el mensaje fue enviado

			System.out.println("Mensaje Enviado");
			JOptionPane.showMessageDialog(null, "El mail de avisose ha enviado correctamente.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"El mail de aviso no se ha podido enviar. Por favor, intente mas tarde.");
		}
	}

	public Reparacion getReparacion() {
		return reparacion;
	}

	public void setReparacion(Reparacion reparacion) {
		this.reparacion = reparacion;
	}

}
