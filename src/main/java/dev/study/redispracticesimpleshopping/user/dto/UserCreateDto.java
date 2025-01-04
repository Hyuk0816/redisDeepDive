package dev.study.redispracticesimpleshopping.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCreateDto {
	@Email
	private String email;
	private String password;
	private String name;
	private Integer age;
	private String mobile;

}
