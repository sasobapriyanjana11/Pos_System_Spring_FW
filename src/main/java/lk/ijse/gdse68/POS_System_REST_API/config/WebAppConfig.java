package lk.ijse.gdse68.POS_System_REST_API.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.gdse68.POS_System_REST_API")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.gdse68.POS_System_REST_API")
@EnableTransactionManagement
public class WebAppConfig {

}
