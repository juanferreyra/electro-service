package modelo;

import java.net.Socket;
import java.util.Calendar;
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
import presentacion.vista.VentanaPresupuesto;

public class EmailPresupuesto extends Thread {
	
	private float envio = 0;
	private Ingreso ingreso;
	private UsuarioDTO usuario;
	private VentanaPresupuesto ventana;


	public EmailPresupuesto (Ingreso ingreso, UsuarioDTO usuario, VentanaPresupuesto ventana) {
		
		this.ingreso = ingreso;
		this.usuario = usuario;
		this.ventana = ventana;
		
	}
	
	@Override
	public void run() {
		
		this.enviarPresupuesto();
		
	}
	
	private void enviarPresupuesto(){
		
		String destinatario = ingreso.getCliente().getMail();
		

		int dia = ventana.getVencimiento_Calendario().getCalendar().get(Calendar.DAY_OF_MONTH);
		int mes = ventana.getVencimiento_Calendario().getCalendar().get(Calendar.MONTH ) +1;
		int anio = ventana.getVencimiento_Calendario().getCalendar().get(Calendar.YEAR);
		
		String fechaVencimiento = dia +" / "+ mes +" / "+ anio;
		
		
		if (ingreso.getIngreso().getEnvio()){
			envio = ingreso.getIngreso().getMonto_envio();
		}
		
		float totalpresupuesto = Float.parseFloat(ventana.getValorPresupuestado_txf().getText()) + envio ;
		
		//String con lista de componentes
		String componentes = "";
		
		for(int i = 0; i <  ventana.getComponentes_table().getRowCount(); i++){
			
			componentes += "<b>Repuesto:</b>&nbsp;&nbsp;"+ (String) ventana.getComponentes_table().getModel().getValueAt(i, 1) + "&nbsp; &nbsp;&nbsp;&nbsp;" + 
							"<b>Cantidad:</b>&nbsp;&nbsp;" + (int) ventana.getComponentes_table().getModel().getValueAt(i, 2) + "&nbsp;&nbsp;&nbsp;&nbsp;" + 
							"<b>PrecioTotal: $ </b>&nbsp;&nbsp;" + (float)ventana.getComponentes_table().getModel().getValueAt(i, 4) + "<br>";
		}
		
		//asunto
		String asunto ="Presupuesto de "+ ingreso.ingr.getDescripcion() +" de Electro Service."; 
		
		
		///Cuerpo de mensaje
		String mensaje = 
				
		"<p>Estimado cliente:  " + ingreso.getCliente().getNombre() + " " + ingreso.getCliente().getApellido() +" </p><br>"+
		"<p style=text-indent:4cm > El presupuesto por la reparaci&#243;n Nro: <b> "+ingreso.getId()+" </b> del producto <b>"+ingreso.ingr.getDescripcion()+"</b> " +
		 "ingresado el d&#237;a  "+ ingreso.getIngreso().getFecha_creacion()+ " consta del siguiente detalle: </p><br> " +
		"<p> <b> Informe t&#233;cnico:     </b>" + ventana.getDescripcionBreve_jTextArea().getText() + "</p><br>" +
		"<p> <b>Repuestos a utilizar:  </b></p><br>" +
		"<p>" + componentes +" </p> <br>"+
		"<p> <b>Total de repuestos:     $  </b>"+ventana.getTotal_lbl().getText()+"</p><br>"+
		"<p> <b>Mano de Obra:     $  </b>" + ventana.getManoDeObra_txf().getText()+"</p><br>"+
		"<p> <b>Total presupuesto:     $  </b>"+ ventana.getValorPresupuestado_txf().getText()+"</p><br>"+
		"<p> <b>Env&#237;o a domicilio:    $  </b> " + envio + "</p><br>" +
		"<p> <b>Total :     $  </b> "+totalpresupuesto +"</p><br>"+
		"<p> <b>Le solicitamos que confirme el presupuesto antes de "+fechaVencimiento +".</b></p><br>"+
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
			JOptionPane.showMessageDialog(null, "Email de Presupuesto enviado correctamente", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);
		}
		catch (Exception e)
		{
			
			JOptionPane.showMessageDialog(null, "Email de Presupuesto, NO SE HA PODIDO ENVIAR, por favor intente mas tarde", "Atencion!",
					JOptionPane.INFORMATION_MESSAGE);	
		}
	}


	
}