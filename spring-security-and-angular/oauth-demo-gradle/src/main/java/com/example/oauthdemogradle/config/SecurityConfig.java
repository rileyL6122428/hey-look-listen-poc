package com.example.oauthdemogradle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class SecurityConfig extends
//	WebSecurityConfigurerAdapter
	ResourceServerConfigurerAdapter
	{
//
//	@Value(value="${auth0.apiAudience}")
//	private String apiAudience;
//	
//	@Value(value="${auth0.issuer}")
//	private String issuer;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		JwtWebSecurityConfigurer
//			
//	}
	
	@Value("${security.oauth2.resource.id}")
	private String resourceId;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	      .mvcMatchers("/api/public").permitAll()
	      .antMatchers("/api/private-scoped").access("#oauth2.hasScope('read:messages')")
	      .mvcMatchers("/api/**").authenticated()
	      .anyRequest().permitAll();
	}
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId);
	}

}
