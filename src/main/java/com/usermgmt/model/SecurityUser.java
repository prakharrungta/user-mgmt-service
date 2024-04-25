package com.usermgmt.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8216474940283106814L;
	private final User user;

    public SecurityUser(User user) {
        this.user = user;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	String[] rolesArr = user.getRoles().split(",");
		List<GrantedAuthority> authorities = new ArrayList<>(rolesArr.length);
		for(String role: rolesArr) {
			role = role.trim();
			if(!role.startsWith("ROLE_"))
				role = "ROLE_".concat(role);
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
