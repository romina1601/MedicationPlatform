package config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableJpaRepositories({"repositories"})
@EntityScan({"entities"})
public class JpaPersistanceConfiguration {

    /**
     * Configures and returns the {@link JpaTransactionManager}.
     *
     * @param entityManagerFactory the {@link EntityManagerFactory} to be used.
     * @return the instance of {@link JpaTransactionManager}.
     */

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
