package com.app.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean send(
			String to,
			String subject,
			String text,
			//String[] cc,
			//String[] bcc,
			ClassPathResource file
			)
	{
		
		boolean flag=false;
		
		try {
			//1. Create MimeMessage object
			MimeMessage message=mailSender.createMimeMessage();
			
			//2. Helper class object
			MimeMessageHelper helper=new MimeMessageHelper(message, file!=null?true:false);
			
			//3. set details
			helper.setTo(to);
			helper.setFrom("udaykumar461992@gmail.com");
			helper.setSubject(subject);
			helper.setText(text);
			//helper.setCc(cc); //array Inputs
			//helper.setBcc(bcc);
			if(file!=null)
				helper.addAttachment(file.getFilename(),file);
			
			//4. send button
			mailSender.send(message);
			
			flag=true;
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	
	
	
	
	
}
