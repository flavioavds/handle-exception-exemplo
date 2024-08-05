package br.com.cassol.cas_ms_exception.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class MessageConfiguration extends AcceptHeaderLocaleResolver {

	private static final Logger logger = LoggerFactory.getLogger(MessageConfiguration.class);

	@Bean
	MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		logger.info("MessageSource configured with basename: {}", messageSource.getBasenameSet());
		return messageSource;
	}

	@Bean
	LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(this.messageSource());
		return bean;
	}

	@Bean
	LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("pt", "BR"));
		return localeResolver;
	}

	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		return localeChangeInterceptor;
	}

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		List<Locale> locales = Arrays.asList(new Locale("en"), new Locale("es"), new Locale("pt", "BR"));
		String headerLanguage = request.getHeader("Accept-Language");
		return headerLanguage == null || headerLanguage.isEmpty() ? Locale.getDefault()
				: Locale.lookup(Locale.LanguageRange.parse(headerLanguage), locales);
	}
}