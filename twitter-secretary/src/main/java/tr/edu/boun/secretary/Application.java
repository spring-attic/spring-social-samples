package tr.edu.boun.secretary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.web.SignInAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tr.edu.boun.secretary.signin.SimpleSignInAdapter;

@SpringBootApplication
@EnableSocial
@EnableSwagger2
@EnableJpaRepositories("tr.edu.boun.secretary.repository")
@EnableConfigurationProperties
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public SignInAdapter signInAdapter() {
		return new SimpleSignInAdapter(new HttpSessionRequestCache());
	}

}