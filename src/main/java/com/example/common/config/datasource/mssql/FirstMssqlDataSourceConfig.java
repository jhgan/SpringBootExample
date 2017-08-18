package com.example.common.config.datasource.mssql;

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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.example.dao.mssql.first", sqlSessionFactoryRef="firstMssqlSqlSessionFactory")
@EnableTransactionManagement
public class FirstMssqlDataSourceConfig {
	@Bean(name="firstMssqlDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="datasource.mssql.first")
	public DataSource firstMssqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="firstMssqlSqlSessionFactory")
	public SqlSessionFactory firstMssqlSqlSessionFacotry(@Qualifier("firstMssqlDataSource") DataSource firstMssqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(firstMssqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="firstMssqlSqlSessionTemplate")
	public SqlSessionTemplate firstMssqlSqlSessionTemplate(SqlSessionFactory firstMssqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(firstMssqlSqlSessionFactory);
	}
}
