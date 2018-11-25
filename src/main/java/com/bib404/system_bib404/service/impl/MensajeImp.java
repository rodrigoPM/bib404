package com.bib404.system_bib404.service.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import com.bib404.system_bib404.service.Mensaje;


@Service("mensajeImp")
public class MensajeImp implements Mensaje{

	@Override
	public void sendMsj(String mensaje, String asunto, String destino) {
		
		String remitente = "BIB404G02@gmail.com";  
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "Bib404:)");    //La clave de la cuenta
		props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO,destino);
	        message.setSubject(asunto);
	        message.setText(mensaje);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "Bib404:)");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        System.out.println("Error: "+me.getMessage());   //Si se produce un error
	    }
		
		
	}

}