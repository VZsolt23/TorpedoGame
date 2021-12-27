package hu.nye.progtech.torpedo.configuration;

import hu.nye.progtech.torpedo.service.utility.MapToString;
import hu.nye.progtech.torpedo.service.utility.MapUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Battleship game utilities.
 */
@Configuration
public class UtilityConfiguration {
    @Bean
    MapUtility mapUtil() {
        return new MapUtility();
    }

    @Bean
    MapToString mapToString() {
        return new MapToString();
    }
}
