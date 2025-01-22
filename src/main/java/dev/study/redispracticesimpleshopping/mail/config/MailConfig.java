package dev.study.redispracticesimpleshopping.mail.config;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "spring.mail")
@EnableConfigurationProperties(MailConfig.class)
@Getter @Setter
@Slf4j
public class MailConfig {
	private String host;
	private int port;
	private String username;
	private String password;


	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);

		log.info("---------------------");
		log.info(mailSender.getHost());
		log.info(mailSender.getUsername());
		log.info(mailSender.getPassword());

		Properties javaMailProperties = mailSender.getJavaMailProperties();
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "false");
		javaMailProperties.put("mail.smtp.ssl.enable", "true");
		javaMailProperties.put("mail.debug", "true");
		return mailSender;

	}
}
