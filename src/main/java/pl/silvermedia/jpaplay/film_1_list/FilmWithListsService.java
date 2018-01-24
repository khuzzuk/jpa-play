package pl.silvermedia.jpaplay.film_1_list;

import static pl.silvermedia.jpaplay.service.EventType.FINISHED_LISTS;
import static pl.silvermedia.jpaplay.service.EventType.GET_LISTS;

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
public class FilmWithListsService
{
   private final Bus<EventType> bus;

   private Emitter<FilmWithLists> emitter;

   @PostConstruct
   private void init()
   {
      emitter = new Emitter<>();
      bus.setReaction(FINISHED_LISTS, emitter::provide);
   }

   @RequestMapping("filmsWithLists/{id}")
   public Mono<FilmWithLists> getAllWithFetching(@PathVariable int id)
   {
      bus.send(GET_LISTS, FINISHED_LISTS, id);
      return emitter;
   }
}
