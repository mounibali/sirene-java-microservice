package com.example.sirene.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Optional;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.select()//
				.apis(RequestHandlerSelectors.basePackage("com.example.sirene"))
				.paths(Predicates.not(PathSelectors.regex("/error")))//
				.build()//
				.apiInfo(metadata())//
				.useDefaultResponseMessages(false)//
				.genericModelSubstitutes(Optional.class);

	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder()//
				.title("SIRENE Rest API's")//
				.description("As part of the Public Data Service, many data sets are open to the general public. " + ""
						+ "The SIRENE database on companies is therefore now Open data. You can consult the database freely and for free!")//
				.version("1.0.0")//
				.contact(new Contact("Ali MOUNIB", null, null)).license("MIT License")
				.licenseUrl("http://opensource.org/licenses/MIT")//
				.build();
	}
}
