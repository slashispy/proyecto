package py.edu.una;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import py.edu.una.rest.filters.AuthFilter;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig {
	
	@Bean
    public FilterRegistrationBean authFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("authFilter");
        registrationBean.addUrlPatterns("/*");
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.setEnabled(Boolean.TRUE);
        registrationBean.setAsyncSupported(Boolean.TRUE);
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
