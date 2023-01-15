package jinheung.project.config.mybatis;

//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
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
// multi datasource sample
//@Configuration
//@MapperScan(basePackages="kr.co.stylebot.collie.product",sqlSessionFactoryRef="productSqlSessionFactory")/*멀티DB사용시 mapper클래스파일 스켄용 basePackages를 DB별로 따로설정*/
//@EnableTransactionManagement
//public class ProductDatasourceConfig {
//
//    @Bean(name="productDataSource")
//    @Primary
//    @ConfigurationProperties(prefix="spring.product.datasource")
//    public DataSource productDataSource() {
//        return DataSourceBuilder.create().build();
//
//    }
//
//    @Bean(name="productSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("productDataSource") DataSource productDataSource, ApplicationContext applicationContext) throws Exception{
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(productDataSource);
//        sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/mapper/*.xml")); //쿼리작성용 mapper.xml위치 설정.
////        sessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/config/*.xml"));
//        return sessionFactory.getObject();
//    }
//
//    @Bean(name="productSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory productSqlSessionFactory) throws Exception{
//        return new SqlSessionTemplate(productSqlSessionFactory);
//    }
//
//    @Bean(name = "productTransactionManager")
//    @Primary
//    public PlatformTransactionManager transactionManager(@Qualifier("productDataSource") DataSource productDataSource) {
//        return new DataSourceTransactionManager(productDataSource);
//    }
//}
