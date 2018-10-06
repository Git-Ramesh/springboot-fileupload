package com.rs.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.rs.app.model.User;

@Configuration
public class HibernateConfig {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private Environment env;

	@Bean
	public LocalSessionFactoryBean localSessionFactoryBean() {
		final LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setAnnotatedClasses(User.class);
		localSessionFactoryBean.setDataSource(this.dataSource);
		Properties hibernateProps = new Properties();
		hibernateProps.put("hibernate.dialect", this.env.getProperty("hibernate.dialect"));
		hibernateProps.put("hibernate.show_sql", this.env.getProperty("hibernate.show_sql"));
		hibernateProps.put("hibernate.format_sql", this.env.getProperty("hibernate.format_sql"));
		hibernateProps.put("hibernate.hbm2ddl.auto", this.env.getProperty("hibernate.hbm2ddl.auto"));
		localSessionFactoryBean.setHibernateProperties(hibernateProps);
		return localSessionFactoryBean;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		return new HibernateTransactionManager(localSessionFactoryBean().getObject());
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(localSessionFactoryBean().getObject());
	}
}