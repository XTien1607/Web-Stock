package ltgroup.com.vn.webstock.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "dataEntityManagerFactory",
    transactionManagerRef = "stockReportTransactionManager",
    basePackages = {"ltgroup.com.vn.webstock.repository"}
)
public class ConfigDataBase {
  private final String MODEL_PACKAGE = "ltgroup.com.vn.webstock.model.entity";

  @Bean(name = "stockDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.stock")
  public DataSource initBeanDataSource() {
    DataSource dataSource = DataSourceBuilder.create().build();
    return dataSource;
  }

  @Bean(name = "dataEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("stockDataSource") DataSource dataSource, EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
    return entityManagerFactoryBuilder
        .dataSource(dataSource)
        .packages(MODEL_PACKAGE)
        .build();
  }

  @Bean(name = "stockReportTransactionManager")
  public PlatformTransactionManager transactionManager(@Qualifier("dataEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
