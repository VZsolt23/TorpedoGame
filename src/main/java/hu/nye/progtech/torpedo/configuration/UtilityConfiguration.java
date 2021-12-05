package hu.nye.progtech.torpedo.configuration;

import hu.nye.progtech.torpedo.service.utility.MapUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilityConfiguration {
    @Bean
    MapUtility mapUtil() {
        return new MapUtility();
    }
}
