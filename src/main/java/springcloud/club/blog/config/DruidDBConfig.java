//package springcloud.club.blog.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//import java.sql.SQLException;
//
//@SuppressWarnings("AlibabaRemoveCommentedCode")
//@Configuration
//@MapperScan(basePackages = DruidDBConfig.PACKAGE, sqlSessionFactoryRef = "businessSqlSessionFactory")
//public class DruidDBConfig {
//
//    // 精确到 business 目录，以便跟其他数据源隔离
//    static final String PACKAGE = "top.jasminecloud.business.**.dao";
//
//    static final String MAPPER_LOCATION = "classpath*:mybatis/business/**/*.xml";
//
//    private Logger logger = LoggerFactory.getLogger(DruidDBConfigBusiness.class);
//
//    @Value("${spring.datasource.business.url}")
//    private String dbUrl;
//
//    @Value("${spring.datasource.business.username}")
//    private String username;
//
//    @Value("${spring.datasource.business.password}")
//    private String password;
//
//    @Value("${spring.datasource.business.driverClassName}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.business.initialSize}")
//    private int initialSize;
//
//    @Value("${spring.datasource.business.minIdle}")
//    private int minIdle;
//
//    @Value("${spring.datasource.business.maxActive}")
//    private int maxActive;
//
//    @Value("${spring.datasource.business.maxWait}")
//    private int maxWait;
//
//    @Value("${spring.datasource.business.timeBetweenEvictionRunsMillis}")
//    private int timeBetweenEvictionRunsMillis;
//
//    @Value("${spring.datasource.business.minEvictableIdleTimeMillis}")
//    private int minEvictableIdleTimeMillis;
//
//    @Value("${spring.datasource.business.validationQuery}")
//    private String validationQuery;
//
//    @Value("${spring.datasource.business.testWhileIdle}")
//    private boolean testWhileIdle;
//
//    @Value("${spring.datasource.business.testOnBorrow}")
//    private boolean testOnBorrow;
//
//    @Value("${spring.datasource.business.testOnReturn}")
//    private boolean testOnReturn;
//
//    @Value("${spring.datasource.business.poolPreparedStatements}")
//    private boolean poolPreparedStatements;
//
//    @Value("${spring.datasource.business.maxPoolPreparedStatementPerConnectionSize}")
//    private int maxPoolPreparedStatementPerConnectionSize;
//
//    @Value("${spring.datasource.business.filters}")
//    private String filters;
//
//    @Value("{spring.datasource.business.connectionProperties}")
//    private String connectionProperties;
//
//    @Bean
//    public DataSource businessDataSource() {
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(this.dbUrl);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//        // configuration
//        datasource.setInitialSize(initialSize);
//        datasource.setMinIdle(minIdle);
//        datasource.setMaxActive(maxActive);
//        datasource.setMaxWait(maxWait);
//        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//        datasource.setValidationQuery(validationQuery);
//        datasource.setTestWhileIdle(testWhileIdle);
//        datasource.setTestOnBorrow(testOnBorrow);
//        datasource.setTestOnReturn(testOnReturn);
//        datasource.setPoolPreparedStatements(poolPreparedStatements);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
//        try {
//            datasource.setFilters(filters);
//        } catch (SQLException e) {
//            logger.error("druid configuration initialization filter", e);
//        }
//        datasource.setConnectionProperties(connectionProperties);
//        return datasource;
//    }
//
//    @Bean(name = "businessTransactionManager")
//    public DataSourceTransactionManager businessTransactionManager() {
//        return new DataSourceTransactionManager(businessDataSource());
//    }
//
//    @Bean(name = "businessSqlSessionFactory")
//    public SqlSessionFactory businessSqlSessionFactory(
//            @Qualifier("businessDataSource") DataSource businessDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(businessDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DruidDBConfigBusiness.MAPPER_LOCATION));
//        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//        return sessionFactory.getObject();
//    }
//
//    @Bean
//    public ServletRegistrationBean druidServlet() {
//        ServletRegistrationBean reg = new ServletRegistrationBean();
//        reg.setServlet(new StatViewServlet());
//        reg.addUrlMappings("/druid/*");
//        reg.addInitParameter("allow", ""); // 白名单
//        return reg;
//    }
//
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new WebStatFilter());
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
//        filterRegistrationBean.addInitParameter("profileEnable", "true");
//        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
//        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
//        filterRegistrationBean.addInitParameter("DruidWebStatFilter", "/*");
//        return filterRegistrationBean;
//    }
//}
