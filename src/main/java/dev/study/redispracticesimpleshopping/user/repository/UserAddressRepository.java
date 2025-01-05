package dev.study.redispracticesimpleshopping.user.repository;

import dev.study.redispracticesimpleshopping.user.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
}
