package dev.study.redispracticesimpleshopping.user.userdetails;

import dev.study.redispracticesimpleshopping.common.exception.user.UserNotFoundException;
import dev.study.redispracticesimpleshopping.user.entity.User;
import dev.study.redispracticesimpleshopping.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetails implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("loadByUserName User Id: " + email);
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new UserNotFoundException(email));

		return new UserDetailsImpl(user);
	}
}
