package dev.study.redispracticesimpleshopping.user.controller;

import dev.study.redispracticesimpleshopping.common.dto.CreateResponseDto;
import dev.study.redispracticesimpleshopping.user.constants.UserConstants;
import dev.study.redispracticesimpleshopping.user.dto.request.UserCreateDto;
import dev.study.redispracticesimpleshopping.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;

	@Operation(
		summary = "Create User REST API",
		description = "REST API to create new User"
	)
	@ApiResponses({
		@ApiResponse(
			responseCode = "201",
			description = "HTTP Status CREATED"
		)
	}
	)
	@PostMapping("/user")
	public ResponseEntity<CreateResponseDto> createUser(@RequestBody UserCreateDto userCreateDto) {
		userService.createUser(userCreateDto);
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(new CreateResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
	}
}
