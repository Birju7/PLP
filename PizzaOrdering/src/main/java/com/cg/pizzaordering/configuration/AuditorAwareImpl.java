package com.cg.pizzaordering.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * @author BHRIGU
 *
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Bhrigu");
	}

}