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
@MapperScan(value="com.example.dao.mssql", sqlSessionFactoryRef="mssqlSqlSessionFactory")
@EnableTransactionManagement
public class MssqlDataSourceConfig {
	@Bean(name="mssqlDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="datasource.mssql")
	public DataSource mssqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="mssqlSqlSessionFactory")
	public SqlSessionFactory mssqlSqlSessionFacotry(@Qualifier("mssqlDataSource") DataSource mssqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(mssqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="mssqlSqlSessionTemplate")
	public SqlSessionTemplate mssqlSqlSessionTemplate(SqlSessionFactory mssqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(mssqlSqlSessionFactory);
	}
}
