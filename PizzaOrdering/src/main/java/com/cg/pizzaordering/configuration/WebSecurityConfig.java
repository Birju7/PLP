package com.cg.pizzaordering.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService jwtUserDetailsService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				.authorizeRequests()
				.antMatchers("/admin/home").hasRole("ADMIN")
				.antMatchers("/admin/addhotel").hasRole("ADMIN")
				.antMatchers("/admin/pagesubmitaddhotelpage").hasRole("ADMIN")
				.antMatchers("/admin/addroom").hasRole("ADMIN")
				.antMatchers("/admin/pagesubmitaddroompage").hasRole("ADMIN")
				.antMatchers("/admin/showcity").hasRole("ADMIN")
				.antMatchers("/admin/showhotel").hasRole("ADMIN")
				.antMatchers("/admin/showallhotel").hasRole("ADMIN")
				.antMatchers("/admin/showroom").hasRole("ADMIN")
				.antMatchers("/admin/showallroom").hasRole("ADMIN")
				.antMatchers("/admin/deltecity").hasRole("ADMIN")
				.antMatchers("/admin/deletecitydata").hasRole("ADMIN")
				.antMatchers("/admin/deltehotel").hasRole("ADMIN")
				.antMatchers("/admin/deletehoteldata").hasRole("ADMIN")
				.antMatchers("/admin/delteroom").hasRole("ADMIN")
				.antMatchers("/admin/deleteroomdata").hasRole("ADMIN")
				.antMatchers("/admin/updatehotel").hasRole("ADMIN")
				.antMatchers("/admin/updatehoteldata").hasRole("ADMIN")
				.antMatchers("/admin/cities").hasRole("ADMIN")
				.antMatchers("/admin/updatehotelview").hasRole("ADMIN")
				.antMatchers("/admin/updateroom").hasRole("ADMIN")
				.antMatchers("/admin/updateroomdata").hasRole("ADMIN")
				.antMatchers("/admin/updateroomview").hasRole("ADMIN")
				.antMatchers("/customer/home").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/register").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/registerpage").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/viewHotelsPage").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/home").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/ViewBooking").hasAnyRole("ADMIN", "USER")
				.antMatchers("/customer/BookingPage").hasAnyRole("ADMIN", "USER")
				.and().formLogin()
				.loginPage("/admin/login")
				.usernameParameter("username").passwordParameter("password")
//				.anyRequest().authenticated()
				// all other requests need to be authenticated
				
				// make sure we use stateless session; session won't be used to
				// store user's state.
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}





