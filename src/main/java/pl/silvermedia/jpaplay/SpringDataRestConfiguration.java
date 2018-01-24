package pl.silvermedia.jpaplay;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import pl.silvermedia.jpaplay.film.Inventory;

@Configuration
public class SpringDataRestConfiguration
{
   public SpringDataRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration)
   {
      repositoryRestConfiguration.exposeIdsFor(Inventory.class);
   }
}
