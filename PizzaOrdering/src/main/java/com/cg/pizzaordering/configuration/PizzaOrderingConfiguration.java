package com.cg.pizzaordering.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author BHRIGU
 *
 */

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PizzaOrderingConfiguration {
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

}



