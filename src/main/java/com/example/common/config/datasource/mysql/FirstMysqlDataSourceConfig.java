package com.example.common.config.datasource.mysql;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.example.dao.mysql.first", sqlSessionFactoryRef="firstMysqlSqlSessionFactory")
@EnableTransactionManagement
public class FirstMysqlDataSourceConfig {
	
	@Bean(name="firstMysqlDataSource", destroyMethod="close")
	@Primary
	@ConfigurationProperties(prefix="datasource.mysql.first")
	public DataSource firstMysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="firstMysqlSqlSessionFactory")
	@Primary
	public SqlSessionFactory firstMysqlSqlSessionFacotry(@Qualifier("firstMysqlDataSource") DataSource firstMysqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(firstMysqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="firstMysqlSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate firstMysqlSqlSessionTemplate(SqlSessionFactory firstMysqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(firstMysqlSqlSessionFactory);
	}
}
