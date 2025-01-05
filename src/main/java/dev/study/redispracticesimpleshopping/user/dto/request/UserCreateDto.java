package dev.study.redispracticesimpleshopping.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCreateDto {
	@Email
	@NotEmpty(message = "이메일을 입력하세요")
	@Schema(description = "email for login", example = "test@test.com")
	private String email;

	@NotEmpty(message = "비밀번호를 입력하세요")
	@Schema(example = "testtest")
	private String password;

	@NotEmpty(message = "이름을 입력하세요")
	@Schema(example = "김재혁")
	private String name;

	@NotEmpty(message = "나이를 입력하세요")
	@Schema(example = "30")
	private Integer age;

	@NotEmpty
	@Schema(example = "01077045766")
	private String mobile;

	@NotEmpty
	@Schema(example = "08803")
	private String zipCode;

	@NotEmpty
	@Schema(example = "서울시")
	private String city;

	@NotEmpty
	@Schema(example = "관악구 남부순환로")
	private String town;

	@NotEmpty
	@Schema(example = "260길 71, 로뎀하우스 302호")
	private String detailAddress;
}
