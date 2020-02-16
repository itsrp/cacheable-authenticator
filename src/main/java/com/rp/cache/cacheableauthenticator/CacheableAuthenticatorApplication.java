package com.rp.cache.cacheableauthenticator;

import com.rp.cache.cacheableauthenticator.auth.CacheableAuthenticationBuilder;
import com.rp.cache.cacheableauthenticator.auth.CacheableAuthenticator;
import com.rp.cache.cacheableauthenticator.auth.RetriableCacheableAuthenticator;
import com.rp.cache.cacheableauthenticator.model.CacheKey;
import com.rp.cache.cacheableauthenticator.model.CacheValue;
import com.rp.cache.cacheableauthenticator.provider.OktaAuthenticationProvider;
import com.rp.cache.cacheableauthenticator.provider.RedisCacheProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheableAuthenticatorApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(CacheableAuthenticatorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CacheableAuthenticatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		CacheableAuthenticator cacheableAuthenticator = new CacheableAuthenticationBuilder<>()
				.cacheProvider(new RedisCacheProvider<CacheKey, CacheValue>())
				.authenticationProvider(new OktaAuthenticationProvider())
				.build();

		cacheableAuthenticator.authenticate("abc", "abc" );
		cacheableAuthenticator.authenticate("abc", "abc" );
	}
}
