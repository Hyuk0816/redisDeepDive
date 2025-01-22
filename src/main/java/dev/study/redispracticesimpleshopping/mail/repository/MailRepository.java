package dev.study.redispracticesimpleshopping.mail.repository;

import dev.study.redispracticesimpleshopping.mail.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {
	Optional<Mail> findByCode(String code);

}
