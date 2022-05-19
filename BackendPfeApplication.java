package com.projet.BackendPfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

import com.projet.BackendPfe.Controller.ConsulationController;

import java.io.File;
import java.util.Random;
@SpringBootApplication
@ComponentScan({"com.projet.BackendPfe","com.projet.BackendPfe.Controller"})
public class BackendPfeApplication {
	/*@Autowired 
	private EmailSenderService senderService;*/
	public static void main(String[] args) {
		
		new File(ConsulationController.uploadDirectory).mkdir();
		SpringApplication.run(BackendPfeApplication.class, args);
	
		

		/*String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
		          +"lmnopqrstuvwxyz!@#$%&";
				Random rnd = new Random();
				StringBuilder sb = new StringBuilder(20);
				for (int i = 0; i < 20; i++)
					sb.append(chars.charAt(rnd.nextInt(chars.length())));
				System.out.println("sdsdsdsdsd"+sb.toString());*/

	}}
/*	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		senderService.sendEmail("chellymariem01@gmail.com","Inscription Validée","Bienvenu dans notre Application Retina ");
	}*/

	