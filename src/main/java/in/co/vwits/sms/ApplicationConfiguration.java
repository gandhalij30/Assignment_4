package in.co.vwits.sms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "in.co.vwits.sms")
@EnableTransactionManagement // this annotation enables the transaction feature of spring
public class ApplicationConfiguration {
	
	@Bean // this annotation also helps us to register instance of class as spring bean
	public LocalEntityManagerFactoryBean get() {
		LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
		factory.setPersistenceUnitName("smsapp");
		return factory;
	}
	
	// following bean is resonsiple for managing trasaction using spring frame work
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager tx = new JpaTransactionManager();
		tx.setEntityManagerFactory(get().getObject());
		return tx;
	}
}
