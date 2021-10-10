package com.atm.emulator;



import com.atm.emulator.config.security.JwtService;
import com.atm.emulator.repository.UserRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;




@SpringBootApplication(scanBasePackages ="com.atm.emulator")
@EnableJpaAuditing
@EnableEncryptableProperties
@PropertySource("classpath:application.properties")
public class ATMEmulatorApplication   {

	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ATMEmulatorApplication.class, args);
	}

	@Bean
	public JwtService jwtService() {
		return new JwtService();
	}

}