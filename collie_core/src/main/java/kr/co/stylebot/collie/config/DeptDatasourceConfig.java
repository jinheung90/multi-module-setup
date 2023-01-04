package kr.co.stylebot.collie.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration

@MapperScan(basePackages="kr.co.stylebot.collie.dept",sqlSessionFactoryRef="deptSqlSessionFactory")
@EnableTransactionManagement
public class DeptDatasourceConfig {

    @Bean(name="deptDataSource")
    @ConfigurationProperties(prefix="spring.dept.datasource")
    public DataSource deptDataSource() {
        return DataSourceBuilder.create().build();

    }

    @Bean(name="deptSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("deptDataSource") DataSource deptDataSource, ApplicationContext applicationContext) throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(deptDataSource);
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*.xml"));
//        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean(name="deptSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory deptSqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(deptSqlSessionFactory);
    }

    @Bean(name = "deptTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("deptDataSource") DataSource deptDatasource) {
        return new DataSourceTransactionManager(deptDatasource);
    }
}
