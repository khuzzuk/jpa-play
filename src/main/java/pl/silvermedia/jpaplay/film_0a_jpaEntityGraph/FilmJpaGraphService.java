package pl.silvermedia.jpaplay.film_0a_jpaEntityGraph;

import static pl.silvermedia.jpaplay.service.EventType.FINISHED_JPA_GRAPH;
import static pl.silvermedia.jpaplay.service.EventType.GET_JPA_GRAPH;

import java.util.Collection;

import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.service.Emitter;
import pl.silvermedia.jpaplay.service.EventType;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class FilmJpaGraphService
{
   private final Bus<EventType> bus;

   private Emitter<Collection<FilmByJpaGraph>> emitter;

   @PostConstruct
   private void init()
   {
      emitter = new Emitter<>();
      bus.setReaction(FINISHED_JPA_GRAPH, emitter::provide);
   }

   @RequestMapping("filmsJpaGraph/{id}")
   public Mono<Collection<FilmByJpaGraph>> getAllWithFetching(@PathVariable long id)
   {
      bus.send(GET_JPA_GRAPH, FINISHED_JPA_GRAPH, id);
      return emitter;
   }
}
