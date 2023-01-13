package kr.co.ch08.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/").permitAll(); // 모든 자원에 대한 모든 사용자 접근 허용
		http.authorizeHttpRequests().antMatchers("/admin/**").hasRole("ADMIN"); // admin 하위의 모든 링크에 대해서 ADMIN에게만 접근 허용
		http.authorizeHttpRequests().antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER"); // member 하위의 모든 링크에 대해서 ADMIN, MEMBER에게만 접근 허용
		http.authorizeHttpRequests().antMatchers("/user2/loginSuccess").hasAnyRole("3", "4", "5");// /user2/loginSuccess는 ADMIN만 접근 허용
		
		// 자동 로그인 설정
		http.rememberMe()
			.key("autoUser")
			.rememberMeParameter("autoUid")
			.tokenValiditySeconds(600)
			.userDetailsService(service);
			
		// 사이트 위변조 요청 방지
		http.csrf().disable();
		
		//로그인 페이지 설정
		http.formLogin()
		.loginPage("/user2/login")
		.defaultSuccessUrl("/user2/loginSuccess")
		.failureUrl("/user2/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		//로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user2/logout"))
		.logoutSuccessUrl("/user2/login?success=200");
		
	}
	
	@Autowired
	private SecurityUserService service;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// 로그인 인증처리 서비스 등록
		
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
	// 이러한 방법은 권장하는 방법은 아님 WebSecurityConfigurerAdapter의 서비스가 종료됨으로 SecurityFilterChain를 Bean으로 등록해서 사용해야함
	
}
