package dev.study.redispracticesimpleshopping.mail.controller;

import dev.study.redispracticesimpleshopping.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mail")
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@ResponseBody
	@PostMapping
	public String sendCode(@RequestParam("send-mail") String sendMail) throws MessagingException {
		return mailService.sendSimpleMail(sendMail);
	}

}
