package halfbind.UNISTBusMate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.http.HttpMethod;

@SpringBootApplication
public class UnistBusMateApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(UnistBusMateApplication.class, args);
	}

	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000")
								.allowedMethods(
    							HttpMethod.GET.name(),
									HttpMethod.HEAD.name(),
									HttpMethod.POST.name(),
									HttpMethod.PUT.name(),
									HttpMethod.DELETE.name()
								);
            }
        };
    }

}
