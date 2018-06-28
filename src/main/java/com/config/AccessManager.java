package com.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("accessManager")
public class AccessManager {

	public boolean hasRole(String... rolesallowed) {

		for (GrantedAuthority authority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			for (String role : rolesallowed) {
				if (authority.getAuthority().replaceAll("ROLE_", "").equalsIgnoreCase(role)) {
					return true;
				}
			}
		}
		return false;
	}
}
