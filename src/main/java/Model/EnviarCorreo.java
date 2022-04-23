/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.beanUsuario;
import java.io.Serializable;
import java.util.Properties;
import java.util.stream.Collectors;
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

    Usuario user = new Usuario();

    public EnviarCorreo(Usuario usuario) {
        super("procesoEnvioEmail");
        user = usuario;
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
                return new PasswordAuthentication("kevinsgdeveloper@gmail.com", "Developer123.");
            }
        });
    }

    private String getDirecciones(Usuario user) {
        String email = user.getEmail();
        return email;
    }

    private void sendEmail(Usuario user) {
	// Obtenemos las direcciones/destinatarios
	String direcciones = getDirecciones(user);
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
		cuerpoMensaje.setContent(user.getContenido(), "text/html");
		multiparte.addBodyPart(cuerpoMensaje);
		
		//Agregamos el origen del mensaje (nuestro email)
		msg.setFrom(new InternetAddress("kevinsgdeveloper@gmail.com"));
		
		//Enviamos el mensaje
		Transport.send(msg);
	} catch (MessagingException e) {
		e.printStackTrace();
	}
}
}
