package com._2je7.pofol.Config.DB;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@MapperScan(value="com._2je7.pofol.Dao", sqlSessionFactoryRef="dbSqlSessionFactory")
@EnableJpaRepositories(
	    basePackages = "com._2je7.pofol.Repository", // Master Repository 경로
	    entityManagerFactoryRef = "dbEntityManagerFactory", 
	    transactionManagerRef = "dbTransactionManager"
	)
@ComponentScan(basePackages = "com._2je7.pofol.Repository")
@EntityScan(basePackages = "com._2je7.pofol.Entity") // Entity 패키지 지정
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class DataBaseConfig {
	
	@Primary
	@Bean(name="dbDataSource")
	@ConfigurationProperties(prefix="spring.db.datasource")
	public DataSource authDataSource() {
		//application.properties에서 정의한 DB 연결 정보를 빌드
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="dbSqlSessionFactory")
	public SqlSessionFactory dbSqlSessionFactory(@Qualifier("dbDataSource") DataSource dbDataSource, ApplicationContext applicationContext) throws Exception{
		//세션 생성 시, 빌드된 DataSource를 세팅하고 SQL문을 관리할 mapper.xml의 경로를 알려준다.
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dbDataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Primary
	@Bean(name="dbSqlSessionTemplate")
	public SqlSessionTemplate dbSqlSessionTemplate(SqlSessionFactory dbSqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(dbSqlSessionFactory);
	}
	
	@Primary
    @Bean(name = "dbEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean dbEntityManagerFactory(@Qualifier("dbDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com._2je7.pofol.Entity")
                .persistenceUnit("entityManager")
                .build();
    }

    @Primary
    @Bean(name = "dbTransactionManager")
    public PlatformTransactionManager dbTransactionManager(
            final @Qualifier("dbEntityManagerFactory") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean
    ) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
    
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
