package Utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class Mail {
	
	public static String host = "smtp.googlemail.com";
	public static String toAdress = "someMail@gmail.com";
	public static String fromAdress = "otherMail@gmail.com";
	public static String pwd = "*********";
	public static int port = 587;
	
	/*
	 * sendMail - via apache Simple send Mail API
	 * */
	public  void sendMail(String emailBody,String emailSubject) throws EmailException {
		
		Email email = new SimpleEmail();
		
    	email.setHostName(host);
    	email.setSmtpPort(port);
    	email.setAuthenticator(new DefaultAuthenticator(toAdress, pwd));
    	email.setSSLOnConnect(true);
    	email.setFrom(fromAdress);
    	email.setSubject(emailSubject);
    	email.setMsg(emailBody);
    	email.addTo(toAdress);
    	email.send();
	}
public  void sendHTMLMail(String emailBody,String emailSubject) throws EmailException {
		
		HtmlEmail email = new HtmlEmail();
		
    	email.setHostName(host);
    	email.setSmtpPort(port);
    	email.setAuthenticator(new DefaultAuthenticator(toAdress, pwd));
    	email.setSSLOnConnect(true);
    	email.setFrom(fromAdress);
    	email.setSubject(emailSubject);
    	email.setHtmlMsg(emailBody);
    	email.addTo(toAdress);
    	email.send();
	}
	
}
