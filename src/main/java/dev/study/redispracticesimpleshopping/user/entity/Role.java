package dev.study.redispracticesimpleshopping.user.entity;

import lombok.Getter;

@Getter
public enum Role {
	USER("USER"),ADMIN("ADMIN");

	private final String name;

	Role(String name) {
		this.name = name;
	}
}
