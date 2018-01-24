package pl.silvermedia.jpaplay.film_0_entityGraph;

import static pl.silvermedia.jpaplay.service.EventType.FINISHED_GRAPH;
import static pl.silvermedia.jpaplay.service.EventType.GET_GRAPH;

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
public class FilmEntityGraphService
{
   private final Bus<EventType> bus;

   private Emitter<Collection<FilmByGraph>> emitter;

   @PostConstruct
   private void init()
   {
      emitter = new Emitter<>();
      bus.setReaction(FINISHED_GRAPH, emitter::provide);
   }

   @RequestMapping("filmsCustom/fetch/{id}")
   public Mono<Collection<FilmByGraph>> getAllWithFetching(@PathVariable int id)
   {
      bus.send(GET_GRAPH, FINISHED_GRAPH, id);
      return emitter;
   }
}
