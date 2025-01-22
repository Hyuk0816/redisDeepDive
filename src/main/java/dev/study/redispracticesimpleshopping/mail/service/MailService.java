package dev.study.redispracticesimpleshopping.mail.service;

import dev.study.redispracticesimpleshopping.mail.entity.Mail;
import dev.study.redispracticesimpleshopping.mail.repository.MailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender mailSender;

	private static final String senderMail = "rlawogur816@naver.com";

	private final MailRepository mailRepository;

	// 랜덤으로 숫자 생성
	public String createNumber() {
		Random random = new Random();
		StringBuilder key = new StringBuilder();

		for (int i = 0; i < 8; i++) { // 인증 코드 8자리
			int index = random.nextInt(3); // 0~2까지 랜덤, 랜덤값으로 switch문 실행

			switch (index) {
				case 0 -> key.append((char) (random.nextInt(26) + 97)); // 소문자
				case 1 -> key.append((char) (random.nextInt(26) + 65)); // 대문자
				case 2 -> key.append(random.nextInt(10)); // 숫자
			}
		}
		return key.toString();
	}

	public MimeMessage createEmail(String mail, String code) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		mimeMessage.setFrom(senderMail);
		mimeMessage.setRecipients(MimeMessage.RecipientType.TO, mail);
		mimeMessage.setSubject("이메일 인증 코드 입니다.");
		String body = "";
		body += "<h3>인증 번호를 입력해 주세요.</h3>";
		body += "<h1>" + code + "</h1>";
		body += "<h3>감사합니다.</h3>";
		mimeMessage.setText(body, "UTF-8", "html");
		return mimeMessage;
	}

	public String sendSimpleMail(String sendEmail) throws MessagingException {
		String code = createNumber();

		MimeMessage email = createEmail(sendEmail, code);


		try{
			mailSender.send(email);
		} catch (MailException e) {
			e.printStackTrace();
			throw new MessagingException(e.getMessage());
		}
		Mail entity = Mail.builder()
						 .code(code)
						 .email(sendEmail)
						 .createdAt(LocalDateTime.now())
						 .build();
		mailRepository.save(entity);
		return code;
	}
}
