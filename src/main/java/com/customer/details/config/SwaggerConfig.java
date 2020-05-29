package com.customer.details.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(paths())
				.build().tags(new Tag("Customer Details Project", "All apis relating to Customer Details"));

	}

	private Predicate<String> paths() {
		return or(regex("/customer/create.*"), regex("/customer/all.*"), regex("/customer/get.*"),
				regex("/customer/delete.*"), regex("/customer/update.*"), regex("/customer/check.*"));

	}

}
