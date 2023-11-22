package com.courseproject.courseproject.Filter;

import com.courseproject.courseproject.Service.JWT.JwtService;
import com.courseproject.courseproject.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserService userService;
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		System.out.println("[JwtAuthenticationFilter]-doFilterInternal");
		//final String authHeader = request.getHeader("Authorization");
		final Cookie[] Cookies = request.getCookies();
		String jwt="";
		final String userEmail;
		if(Cookies!=null){
			for (var Cookie:Cookies) {
				if(Cookie.getName().equals("jwt")){
					jwt = Cookie.getValue();
					break;
				}
			}
		}
		if (StringUtils.isEmpty(jwt)){
			filterChain.doFilter(request,response);
			return;
		}
		System.out.println("[JwtAuthenticationFilter]-JWT - " + jwt.toString());
		userEmail = jwtService.extractUserName(jwt);
		if(!StringUtils.isEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null){
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtService.isTokenValid(jwt,userDetails)){
				log.debug("User - {}",userDetails);
				System.out.println("[JwtAuthenticationFilter]-User - { "+userDetails.getUsername()+" "+userDetails.getPassword()+" }");
				SecurityContext context = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authenticationToken);
				SecurityContextHolder.setContext(context);
			}
		}
		filterChain.doFilter(request,response);
		
		//final String jwt;
		//final String userEmail;
		//if(StringUtils.isEmpty(authHeader)||!StringUtils.startsWith(authHeader,"Bearer ")){
		//	filterChain.doFilter(request,response);
		//	return;
		//}
		//jwt = authHeader.substring(7);
		//log.debug("JWT - {}",jwt.toString());
		//System.out.println("[JwtAuthenticationFilter]-JWT - " + jwt.toString());
		//for (var Cookie:Cookies) {
		//	System.out.println("Cookie: "+Cookie.getName());
		//}
		//userEmail = jwtService.extractUserName(jwt);
		//if(!StringUtils.isEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null){
		//	UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
		//	if(jwtService.isTokenValid(jwt,userDetails)){
		//		log.debug("User - {}",userDetails);
		//		System.out.println("[JwtAuthenticationFilter]-User - {}"+userDetails);
		//		SecurityContext context = SecurityContextHolder.createEmptyContext();
		//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
		//				userDetails,null,userDetails.getAuthorities());
		//		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		//		context.setAuthentication(authenticationToken);
		//		SecurityContextHolder.setContext(context);
		//	}
		//}
		//filterChain.doFilter(request,response);
	}
}
