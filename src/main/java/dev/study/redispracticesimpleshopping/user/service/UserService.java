package dev.study.redispracticesimpleshopping.user.service;

import dev.study.redispracticesimpleshopping.common.exception.user.EmailDuplicatedException;
import dev.study.redispracticesimpleshopping.user.dto.request.UserCreateDto;
import dev.study.redispracticesimpleshopping.user.entity.Role;
import dev.study.redispracticesimpleshopping.user.entity.User;
import dev.study.redispracticesimpleshopping.user.entity.UserAddress;
import dev.study.redispracticesimpleshopping.user.repository.UserAddressRepository;
import dev.study.redispracticesimpleshopping.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;
	private final PasswordEncoder passwordEncoder;

	public void createUser(UserCreateDto userCreateDto) {
		Optional<User> isUser = userRepository.findByEmail(userCreateDto.getEmail());

		if(isUser.isPresent()) {
			throw new EmailDuplicatedException(userCreateDto.getEmail());
		}

		User user = User.builder()
						.email(userCreateDto.getEmail())
						.password(passwordEncoder.encode(userCreateDto.getPassword()))
						.name(userCreateDto.getName())
						.age(userCreateDto.getAge())
						.mobile(userCreateDto.getMobile())
						.role(Role.USER)
						.build();

		userRepository.save(user);

		UserAddress userAddress = UserAddress.builder()
											 .user(user)
											 .zipCode(userCreateDto.getZipCode())
											 .city(userCreateDto.getCity())
											 .town(userCreateDto.getTown())
											 .detailAddress(userCreateDto.getDetailAddress())
											 .build();
		userAddressRepository.save(userAddress);

	}

}
