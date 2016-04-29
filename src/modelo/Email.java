package modelo;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {
	
	private String destinatarios;
	private String mensaje;
	private String asunto;
	
	public Email(String asunto, String destinatarios, String mensaje){
		
		this.asunto = asunto;
		this.destinatarios = destinatarios;
		this.mensaje = mensaje;
		
	}
	
	public void enviar(){
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
						
			texto.setContent("<font color=red> <h1>Electro Service</font></h1></font> <br>"
					+ "Darregueyra 3896,Los Polvorines, Malvinas Argentinas, Buenos Aires<br>"
					+ "<font color=Navy><h3>Teléfono: 4685 -5438  </h3></font> <br>"
					+this.mensaje, "text/html");

			// Se compone el adjunto con la imagen
			BodyPart adjunto = new MimeBodyPart();
			//adjunto.setDataHandler(
			//		new DataHandler(new FileDataSource("PresentacionInicial.pdf")));
			//adjunto.setFileName("Presentacion Inicial.pdf");

			// Una MultiParte para agrupar texto e imagen.
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(texto);
			multiParte.addBodyPart(adjunto);

			// Se compone el correo, dando to, from, subject y el
			// contenido.
			MimeMessage message = new MimeMessage(session);

			// de 
			message.setFrom(new InternetAddress("reparaciones.electroservice@gmail.com"));

			//para
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.destinatarios));
			
			//+ "juangabrielferreyra93@gmail.com,"
			//+ "sabrina.sacz@gmail.com,"
			//+ "joaquin.tellechea@gmail.com"));

			message.setSubject(this.asunto);
			message.setContent(multiParte);

			// Se envia el correo.
			Transport t = session.getTransport("smtp");
			t.connect("reparaciones.electroservice@gmail.com", "ungs2016");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

			//Con esta imprimimos en consola que el mensaje fue enviado
			System.out.println("Mensaje Enviado");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public static void main(String[] args){
		
		//Email nuevo = new Email("mail con html","pintososcar@hotmail.com", "HOLAs");
		//nuevo.enviar();

	}

	
	

}
