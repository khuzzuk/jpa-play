package pl.silvermedia.jpaplay.repos;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.db.Film;

@Component
@AllArgsConstructor
public class Retriever
{
   private FilmRepository filmRepository;
   private Bus bus;

   @PostConstruct
   private void init()
   {
      bus.setResponse("get", this::pullEntities);
      bus.setResponse("getWithFetch", this::pullWithFetching);
   }

   private List<Film> pullEntities()
   {
      return filmRepository.findAll();
   }

   private Collection<Film> pullWithFetching()
   {
      return filmRepository.findAllWithInventories();
   }
}
