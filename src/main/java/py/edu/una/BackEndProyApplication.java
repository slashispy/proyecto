package py.edu.una;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages={
		"py.edu.una.rest.controllers",
		"py.edu.una.rest.services",
		"py.edu.una.rest.dao"})
@Import({JpaConfiguration.class, WebConfig.class})
//@Import(JpaConfiguration.class)
public class BackEndProyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BackEndProyApplication.class, args);
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackEndProyApplication.class);
    }
}
