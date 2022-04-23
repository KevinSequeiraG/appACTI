/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



/**
 *
 * @author Oscar
 */
public class EnviarCorreo extends Thread implements Serializable {


    public EnviarCorreo() {
        super("procesoEnvioEmail");
    }

    private Session connectServer() {
        // propiedades de conexion
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("asistenteproyectosprogra@gmail.com", "Developer123");
            }
        });
    }

    private String getDirecciones(String email) {
        return email;
    }

    public void sendEmail(String email, String nombre, int codigo) {
	// Obtenemos las direcciones/destinatarios
	String direcciones = getDirecciones(email);
	// Obtenemos la conexion al servidor de correos
	Session session = connectServer();
	try {
		MimeMessage msg = new MimeMessage(session);
		//Agregamos los headers necesarios
		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");
		// Asignamos el asunto del correo
		msg.setSubject("Código de ingreso", "UTF-8");

		Multipart multiparte = new MimeMultipart();
	
		//Creamos el cuerpo del mensaje
		BodyPart cuerpoMensaje = new MimeBodyPart();
                // en ese get contenido va el codigo que le tenemos que enviar
		cuerpoMensaje.setContent("<h1>Hola, "+nombre+".</h1> <h2>Felicitaciones por completar su autoregistro, su código de ingreso por primera vez es: "+codigo+".</h2><h2>Recuerde que no podrá ingresar hasta que un administrador le apruebe su solicitud, además deberá cambiar la contraseña lo antes posible.</h2><br></br>ProjectACTI", "text/html");
		multiparte.addBodyPart(cuerpoMensaje);
		msg.setContent(multiparte);
		msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(direcciones));
		//Agregamos el origen del mensaje (nuestro email)
		msg.setFrom(new InternetAddress("asistenteproyectosprogra@gmail.com"));
		
		//Enviamos el mensaje
		Transport.send(msg);
	} catch (MessagingException e) {
		e.printStackTrace();
	}
}
}
