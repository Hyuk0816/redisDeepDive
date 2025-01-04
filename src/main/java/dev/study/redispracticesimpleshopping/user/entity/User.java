package dev.study.redispracticesimpleshopping.user.entity;

import dev.study.redispracticesimpleshopping.order.entity.Orders;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Integer age;

	@Column(name = "mobile")
	private String mobile;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Orders> orders;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

}
