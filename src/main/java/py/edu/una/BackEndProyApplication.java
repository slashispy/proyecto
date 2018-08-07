package py.edu.una;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages={
		"py.edu.una.rest.controllers",
		"py.edu.una.rest.services",
		"py.edu.una.rest.dao"})
@Import(JpaConfiguration.class)
public class BackEndProyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndProyApplication.class, args);
	}
}
