package co.vinod.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import co.vinod.controllers.ControllerPackage;
import co.vinod.dao.DaoPackage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { DaoPackage.class, ControllerPackage.class })
@PropertySource({ "classpath:jdbc-info.properties" })
public class AppConfig implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.trace("initializing the application...");

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);

		DispatcherServlet ds = new DispatcherServlet(ctx);
		Dynamic servlet = servletContext.addServlet("ds", ds);
		servlet.addMapping("/");
		servlet.setLoadOnStartup(0);
	}

	@Bean
	public DataSource dataSource(@Value("${jdbc.connection.url}") String url,
			@Value("${jdbc.connection.username}") String username,
			@Value("${jdbc.connection.password}") String password) {

		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl(url);
		ds.setUser(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		bean.setPackagesToScan("co.vinod.entity");
		bean.afterPropertiesSet();
		return bean.getObject();
	}

	@Bean
	public EntityManager entityManager(EntityManagerFactory factory) {
		return factory.createEntityManager();
	}

}
