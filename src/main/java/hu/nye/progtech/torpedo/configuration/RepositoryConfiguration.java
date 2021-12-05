package hu.nye.progtech.torpedo.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import hu.nye.progtech.torpedo.persistance.GameSavesRepository;
import hu.nye.progtech.torpedo.persistance.implementation.JdbcGameSavesRepository;
import hu.nye.progtech.torpedo.persistance.implementation.MapParser;
import hu.nye.progtech.torpedo.service.utility.MapToString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
    /*@Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "Zsolt", "password");
    }

    @Bean(destroyMethod = "close")
    GameSavesRepository gameSavesRepository(Connection connection, MapToString mapToString, MapParser mapParser) {
        return new JdbcGameSavesRepository(connection, mapToString, mapParser);
    }*/
}
