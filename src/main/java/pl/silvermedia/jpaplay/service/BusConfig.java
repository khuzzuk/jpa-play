package pl.silvermedia.jpaplay.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.khuzzuk.messaging.Bus;

@Configuration
//@EnableJpaRepositories(basePackages = "pl.silvermedia.jpaplay.repos")
//@EntityScan(basePackages = "pl.silvermedia.jpaplay.db")
public class BusConfig
{
   @Bean
   public Bus bus()
   {
      return Bus.initializeBus(false);
   }
}
