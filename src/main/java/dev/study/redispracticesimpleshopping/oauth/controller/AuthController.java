package dev.study.redispracticesimpleshopping.oauth.controller;

import dev.study.redispracticesimpleshopping.common.dto.SuccessResponseDto;
import dev.study.redispracticesimpleshopping.oauth.constants.AuthConstants;
import dev.study.redispracticesimpleshopping.oauth.dto.LoginDto;
import dev.study.redispracticesimpleshopping.oauth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	@PostMapping("")
	public ResponseEntity<SuccessResponseDto> login(@RequestBody LoginDto loginDto,
		HttpServletResponse response
		) {
		authService.login(loginDto, response);
		return ResponseEntity.ok(new SuccessResponseDto(AuthConstants.STATUS_200,AuthConstants.MESSAGE_Login_200));
	}

	@PostMapping("/logout")
	public ResponseEntity<SuccessResponseDto> logout(HttpServletRequest request,HttpServletResponse response) {
		authService.logout(request, response);
		return ResponseEntity.ok(new SuccessResponseDto(AuthConstants.STATUS_200,AuthConstants.MESSAGE_Logout_200));
	}
}
