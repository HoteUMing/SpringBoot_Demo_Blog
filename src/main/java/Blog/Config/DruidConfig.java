package Blog.Config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    //配置数据源
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置 Druid 的监控
//    @Bean
//    public ServletRegistrationBean servlet() {
//        ServletRegistrationBean<StatViewServlet> servlet = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        Map<String, String> map = new HashMap<>();
//        map.put("loginUsername","admin");
//        map.put("loginPassword","123456");
//        map.put("allow","");//默认就是允许所有访问
//        map.put("deny","192.168.15.21");
//        servlet.setInitParameters(map);
//        return servlet;
//    }
//
//    @Bean
//    public FilterRegistrationBean filter() {
//        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//        filter.setFilter(new WebStatFilter());
//        Map<String, String> map = new HashMap<>();
//        map.put("exclusions", "*.js,*.css,/druid/*");
//        filter.setInitParameters(map);
//        filter.setUrlPatterns(Arrays.asList("/*"));
//        return filter;
//    }

}
