package com.tuxoftware.api_gateway.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource liquibaseDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SpringLiquibase liquibase(DataSource liquibaseDataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(liquibaseDataSource);

        liquibase.setChangeLog(liquibaseProperties.getChangeLog() != null ?
                liquibaseProperties.getChangeLog() :
                "classpath:db/changelog/db.changelog-master.xml");

        // --- CORRECCIÓN AQUÍ ---
        // Convertimos List<String> a String separado por comas
        if (liquibaseProperties.getContexts() != null) {
            liquibase.setContexts(String.join(",", liquibaseProperties.getContexts()));
        }

        // Lo mismo aplica para labels si los usaras en el futuro, pero contexts es el crítico ahora.
        if (liquibaseProperties.getDefaultSchema() != null) {
            liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        }

        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());

        return liquibase;
    }
}
