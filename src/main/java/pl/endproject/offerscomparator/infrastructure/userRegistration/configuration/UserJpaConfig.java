package pl.endproject.offerscomparator.infrastructure.userRegistration.configuration;

/*
todo do przegadania jak utworzyc zapytania JPA: źródło: https://www.baeldung.com/spring-jpa-test-in-memory-database
@Configuration
@EnableJpaRepositories(basePackages = "pl.endproject.offerscomparator.infrastructure.userRegistration.dao")
@PropertySource("application.properties")
@EnableTransactionManagement
public class UserJpaConfig {

        @Autowired
        private Environment env;

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
            dataSource.setUrl(env.getProperty("jdbc.url"));
            dataSource.setUsername(env.getProperty("jdbc.user"));
            dataSource.setPassword(env.getProperty("jdbc.pass"));

            return dataSource;
        }
}*/
