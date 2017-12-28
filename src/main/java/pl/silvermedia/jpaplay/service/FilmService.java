package pl.silvermedia.jpaplay.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.db.Film;
import pl.silvermedia.jpaplay.db.Titled;
import pl.silvermedia.jpaplay.repos.FilmRepository;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FilmService
{
   private final FilmRepository filmRepository;
   private final Bus bus;
   private Emitter emitter;
   private Emitter fetchEmitter;

   @PostConstruct
   private void setReactions()
   {
      emitter = new Emitter();
      bus.setReaction("finished", emitter::provide);

      fetchEmitter = new Emitter();
      bus.setReaction("finishedWithInventories", fetchEmitter::provide);
   }

   @RequestMapping("films")
   public Mono<Collection<Film>> getAll()
   {
      bus.sendCommunicate("get", "finished");
      return emitter;
   }

   @RequestMapping("films/safe")
   public Collection<Film> getAllSafe()
   {
      return filmRepository.findAll();
   }

   @RequestMapping("films/fetch")
   public Mono<Collection<Film>> getAllWithFetching()
   {
      bus.sendCommunicate("getWithFetch", "finishedWithInventories");
      return fetchEmitter;
   }

   @RequestMapping("films/{id}")
   public Film findAllFlux(@PathVariable long id)
   {
      return filmRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
   }

   @RequestMapping("films/titles/{title}")
   public Film findByTitle(@PathVariable String title)
   {
      return filmRepository.findByTitle(title);
   }

   @RequestMapping("films/titleById/{id}")
   public Titled getTitlesOnly(@PathVariable int id)
   {
      return filmRepository.getTopById(id);
   }
}
