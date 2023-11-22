package com.courseproject.courseproject.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name ="USER_PROFILE")
public class User implements UserDetails {
	@Id
	@GeneratedValue()
	Long USER_PROFILE_ID;
	String LOGIN;
	byte[] AVATAR;
	@Column(unique = true)
	String EMAIL;
	String PASSWORD;
	@Enumerated(EnumType.STRING)
	Role USER_ROLE;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(USER_ROLE.name()));
	}
	
	@Override
	public String getPassword() {
		return PASSWORD;
	}
	
	@Override
	public String getUsername() {
		return EMAIL;
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
