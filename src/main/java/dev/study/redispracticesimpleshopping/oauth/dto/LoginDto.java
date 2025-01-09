package dev.study.redispracticesimpleshopping.oauth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginDto {
	@Schema(example = "test@test.com")
	private String email;
	@Schema(example = "testtest")
	private String password;
}
