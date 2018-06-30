package com.helper;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.exception.SoccerManagementException;

public class AppUtility {

	public static String getOwner() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public static Set<String> getRoles() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
	}

	public static void checkNameLength(String... names) {
		for (String name : names) {
			if (name.length() > 15) {
				throw new SoccerManagementException("Name can not be more than 15 characters.");
			}
		}
	}

	public static void checkAge(int age) {
		if (age < 18 || age > 40) {
			throw new SoccerManagementException("Player age should be between 18 & 40.");
		}
	}
}
