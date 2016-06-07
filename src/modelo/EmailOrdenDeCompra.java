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
import presentacion.vista.VentanaOrdenCompra;

public class EmailOrdenDeCompra extends Thread {
	
	private VentanaOrdenCompra ventanaordenDeCompra;
	private UsuarioDTO usuario;
	
	public EmailOrdenDeCompra( UsuarioDTO usuario, VentanaOrdenCompra ventanaOrdenDeCompra) {
		
		this.ventanaordenDeCompra = ventanaOrdenDeCompra;
		this.usuario = usuario;
		
	}
	
	@Override
	public void run() {
		
		this.enviarPresupuesto();
		
	}
	
	private void enviarPresupuesto(){
		
		String destinatario = ventanaordenDeCompra.getMailTexto_lbl().getText();
				
		//String con lista de componentes
		String componentes = "";
		
		for(int i = 0; i <  this.ventanaordenDeCompra.getComponentes_table().getRowCount(); i++){
			
		componentes += 
				"<b>Marca:</b>&nbsp;&nbsp;"+ (String) this.ventanaordenDeCompra.getComponentes_table().getModel().getValueAt(i, 1) + "&nbsp; &nbsp;&nbsp;&nbsp;" +
				"<b>Repuesto:</b>&nbsp;&nbsp;"+ (String) this.ventanaordenDeCompra.getComponentes_table().getModel().getValueAt(i, 2) + "&nbsp; &nbsp;&nbsp;&nbsp;" + 
				"<b>Cantidad: </b>&nbsp;&nbsp;" + (int) this.ventanaordenDeCompra.getComponentes_table().getModel().getValueAt(i, 3) + "&nbsp;&nbsp;&nbsp;&nbsp;" +
				"<br>";
		}
		
		//asunto
		String asunto ="Solicitud  compra de repuestos de Electro Service."; 
		
		
		///Cuerpo de mensaje
		String mensaje = 
				
		"<p>Estimado :  " + ventanaordenDeCompra.getNombreRazonSocialTexto_lbl().getText() +" </p><br>"+
		"<p style=text-indent:4cm > Solicitamos que a la brevedad nos envie los repuestos que a continuacion detallamos.  </p><br>" +
				
		"<p> <b>Listado de repuestos :  </b></p><br>" +
		"<p>" + componentes +" </p> <br>"+
		"<p> <b>Le solicitamos que se ponga en contacto con nosotros al telefono: 4685 -5438  o por emial a " +
		" reparaciones.electroservice@gmail.com </b></p><br>"+
		
		"<p> <b>ATTE:  </b>"+usuario.getNombre()+"   "+ usuario.getApellido()+"</p>";
		
		
		if (this.verificarConeccion()){

			this.enviar(destinatario, mensaje, asunto);
		}else{

			JOptionPane.showMessageDialog(null, "CORREO NO ENVIADO, No tiene acceso a internet", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}


	}
	
	private boolean verificarConeccion() {

		String dirWeb = "www.google.com.ar";
		int puerto = 80;

		try{
			@SuppressWarnings("resource")
			Socket prueba = new Socket(dirWeb, puerto);

			if(prueba.isConnected()){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			
			e.printStackTrace();	
		}
		return false;
	}

		
	private void enviar(String destinatarios ,String mensaje, String asunto){
		try
		{
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
						
			texto.setContent("<font color=red> <h1 align=center>Electro R S.R.L.</font></h1></font> <br>" +
							"<p align=center>Darregueyra 3896,Los Polvorines, Malvinas Argentinas, Buenos Aires<br></p>" +
					        "<font color=Navy><h3 align=center>Tel&#233;fono: 4685 -5438  </h3></font> <br>" +
					        mensaje, "text/html");

			// Se compone el adjunto con la imagen
			@SuppressWarnings("unused")
			BodyPart adjunto = new MimeBodyPart();
			//adjunto.setDataHandler(
			//		new DataHandler(new FileDataSource("PresentacionInicial.pdf")));
			//adjunto.setFileName("Presentacion Inicial.pdf");

			// Una MultiParte para agrupar texto e imagen.
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			//multiParte.addBodyPart(adjunto);

			// Se compone el correo, dando to, from, subject y el
			// contenido.
			MimeMessage message = new MimeMessage(session);

			// de 
			message.setFrom(new InternetAddress("reparaciones.electroservice@gmail.com"));

			//para
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatarios));

			message.setSubject(asunto);
			message.setContent(multiParte);

			// Se envia el correo.
			Transport t = session.getTransport("smtp");
			t.connect("reparaciones.electroservice@gmail.com", "ungs2016");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

			//Con esta imprimimos en consola que el mensaje fue enviado
			
			System.out.println("Mensaje Enviado");
			JOptionPane.showMessageDialog(null, "Email orden de compra enviado correctamente", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(null, "Email orden de compra , NO SE HA PODIDO ENVIAR, por favor intente mas tarde", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);	
		}
	}

	
	

}
