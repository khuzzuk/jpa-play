package pl.silvermedia.jpaplay.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.khuzzuk.messaging.Bus;

@Configuration
public class BusConfig
{
   @Bean
   public Bus bus()
   {
      return Bus.initializeBus(EventType.class, false);
   }
}
