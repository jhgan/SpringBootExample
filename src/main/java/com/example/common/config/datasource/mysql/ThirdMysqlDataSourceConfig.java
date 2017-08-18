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
@MapperScan(value="com.example.dao.mysql.third", sqlSessionFactoryRef="thirdMysqlSqlSessionFactory")
@EnableTransactionManagement
public class ThirdMysqlDataSourceConfig {
	@Bean(name="thirdMysqlDataSource", destroyMethod="close")
	@ConfigurationProperties(prefix="datasource.mysql.third")
	public DataSource thirdMysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="thirdMysqlSqlSessionFactory")
	public SqlSessionFactory thirdMysqlSqlSessionFacotry(@Qualifier("thirdMysqlDataSource") DataSource thirdMysqlDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(thirdMysqlDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="thirdMysqlSqlSessionTemplate")
	public SqlSessionTemplate thirdMysqlSqlSessionTemplate(SqlSessionFactory thirdMysqlSqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(thirdMysqlSqlSessionFactory);
	}
}
