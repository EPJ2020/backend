package ch.lfg.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configures the basic information for OpenAPI
 */
@Configuration
@EnableAsync
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI()
      .components(new Components())
      .info(new Info().title("EPJ-LFG API").description(
        "This is a API created for the LFG project"));
  }
}
