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
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value="com.example.dao.mysql.second", sqlSessionFactoryRef="secondMysqlSqlSessionFactory")
@EnableTransactionManagement
public class SecondMysqlDataSourceConfig {
	@Bean(name="secondMysqlDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="datasource.mysql.second")
	public DataSource secondMysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="secondMysqlSqlSessionFactory")
	public SqlSessionFactory secondMysqlSqlSessionFacotry(@Qualifier("secondMysqlDataSource") DataSource secondMysqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(secondMysqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="secondMysqlSqlSessionTemplate")
	public SqlSessionTemplate secondMysqlSqlSessionTemplate(SqlSessionFactory secondMysqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(secondMysqlSqlSessionFactory);
	}
}
