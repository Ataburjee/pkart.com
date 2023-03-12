package com.atabur.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.atabur.configuration.jwt.AuthEntry;
import com.atabur.configuration.jwt.AuthToken;

@Configuration
public class MyConfiguration {
	
	  @Autowired
	  private CustomUser customUser;

	  @Autowired
	  private AuthEntry unauthorizedHandler;

	  @Bean
	  public AuthToken authenticationJwtTokenFilter() {
	    return new AuthToken();
	  }
	  
	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(customUser);
	      authProvider.setPasswordEncoder(passwordEncode());
	   
	      return authProvider;
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests()
	        .antMatchers("/pkart.com/user/signin","/pkart.com/admin/signup","/pkart.com/customer/signup","/pkart/user/products/all").permitAll()
			.antMatchers("/pkart.com/admin/**").hasAuthority("ADMIN")
			.antMatchers("/pkart.com/customer/**").hasAuthority("CUSTOMER")
			;
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	
	@Bean
	public PasswordEncoder passwordEncode() {	
		return new BCryptPasswordEncoder();
	}

}
