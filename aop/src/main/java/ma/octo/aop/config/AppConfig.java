package ma.octo.aop.config;

import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(value = "ma.octo",
    excludeFilters = @Filter(type = FilterType.REGEX, pattern = "ma.octo.aop.entity.*"))
@PropertySource("classpath:application.properties")
@Import({DevConfigProperties.class, ProdConfigProperties.class})
public class AppConfig {
    @Bean(name = "appDataSource")
    @Primary
    public DataSource dataSource() {
        var driverManagerDataSource  = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.h2.Driver");
        driverManagerDataSource.setUrl("jdbc:h2:mem:MYDB;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
        driverManagerDataSource.setSchema("PUBLIC");
        driverManagerDataSource.setUsername("yassine");
        return driverManagerDataSource;
    }

}
