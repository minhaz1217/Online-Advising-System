package io.github.minhaz1217.onlineadvising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineAdvisingApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OnlineAdvisingApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OnlineAdvisingApplication.class, args);
	}
}
