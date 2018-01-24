package pl.silvermedia.jpaplay.film;

import static pl.silvermedia.jpaplay.service.EventType.FINISHED;
import static pl.silvermedia.jpaplay.service.EventType.GET;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.service.Emitter;
import pl.silvermedia.jpaplay.service.EventType;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FilmService
{
   private final FilmRepository filmRepository;
   private final Bus<EventType> bus;
   private Emitter<Collection<Film>> emitter;

   @PostConstruct
   private void setReactions()
   {
      emitter = new Emitter<>();
      bus.setReaction(FINISHED, emitter::provide);
   }

   @RequestMapping("filmsCustom/safe")
   public Collection<Film> getAllSafe()
   {
      return filmRepository.findAll();
   }

   @RequestMapping("filmsCustom")
   public Mono<Collection<Film>> getAll()
   {
      bus.sendMessage(GET, FINISHED);
      return emitter;
   }

   @RequestMapping("filmsCustom/{id}")
   public Film find(@PathVariable long id)
   {
      return filmRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
   }

   @RequestMapping("filmsCustom/titles/{title}")
   public Film findByTitle(@PathVariable String title)
   {
      return filmRepository.findByTitle(title);
   }

   @RequestMapping("filmsCustom/titleById/{id}")
   public Titled getTitlesOnly(@PathVariable int id)
   {
      return filmRepository.getTopById(id);
   }
}
