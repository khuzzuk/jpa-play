package pl.silvermedia.jpaplay.filmEager;

import static pl.silvermedia.jpaplay.service.EventType.FINISHED_EAGER;
import static pl.silvermedia.jpaplay.service.EventType.GET_EAGER;

import java.util.Collection;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.film.Film;
import pl.silvermedia.jpaplay.service.Emitter;
import pl.silvermedia.jpaplay.service.EventType;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FilmEagerService
{
   private Emitter emitter;
   private final Bus<EventType> bus;

   @PostConstruct
   private void setReactions()
   {
      emitter = new Emitter();
      bus.setReaction(FINISHED_EAGER, emitter::provide);
   }

   @RequestMapping("filmsEagerCustom")
   public Mono<Collection<Film>> getAll()
   {
      bus.sendMessage(GET_EAGER, FINISHED_EAGER);
      return emitter;
   }
}
