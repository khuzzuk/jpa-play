package pl.silvermedia.jpaplay.service;

import static pl.silvermedia.jpaplay.service.EventType.GET;
import static pl.silvermedia.jpaplay.service.EventType.GET_EAGER;
import static pl.silvermedia.jpaplay.service.EventType.GET_GRAPH;
import static pl.silvermedia.jpaplay.service.EventType.GET_JPA_GRAPH;
import static pl.silvermedia.jpaplay.service.EventType.GET_LISTS;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.khuzzuk.messaging.Bus;
import pl.silvermedia.jpaplay.film.Film;
import pl.silvermedia.jpaplay.film.FilmRepository;
import pl.silvermedia.jpaplay.filmEager.FilmEager;
import pl.silvermedia.jpaplay.filmEager.FilmEagerRepository;
import pl.silvermedia.jpaplay.film_0_entityGraph.FilmByGraph;
import pl.silvermedia.jpaplay.film_0_entityGraph.FilmEntityGraphRepository;
import pl.silvermedia.jpaplay.film_0a_jpaEntityGraph.FilmByJpaGraph;
import pl.silvermedia.jpaplay.film_0a_jpaEntityGraph.FilmJpaGraphRepository;
import pl.silvermedia.jpaplay.film_1_list.FilmWithLists;
import pl.silvermedia.jpaplay.film_1_list.FilmWithListsRepository;

@Component
@AllArgsConstructor
public class Retriever
{
   private FilmRepository filmRepository;
   private FilmEntityGraphRepository filmEntityGraphRepository;
   private FilmEagerRepository filmEagerRepository;
   private FilmWithListsRepository filmWithListsRepository;
   private FilmJpaGraphRepository filmJpaGraphRepository;
   private Bus<EventType> bus;

   @PostConstruct
   private void init()
   {
      bus.setResponse(GET, this::pullEntities);
      bus.setResponse(GET_GRAPH, this::withEntityGraph);
      bus.setResponse(GET_EAGER, this::pullEntitiesEagerly);
      bus.setResponse(GET_LISTS, this::findFilmWithLists);
      bus.setResponse(GET_JPA_GRAPH, this::findBilmWithJpaGraph);
   }

   private List<Film> pullEntities()
   {
      return filmRepository.findAll();
   }

   private Collection<FilmByGraph> withEntityGraph(int id)
   {
      return filmEntityGraphRepository.findByIdWithRentals(id);
   }

   private List<FilmEager> pullEntitiesEagerly()
   {
      return filmEagerRepository.findAll();
   }

   private FilmWithLists findFilmWithLists(int id)
   {
      return filmWithListsRepository.findById(id);
   }

   private Collection<FilmByJpaGraph> findBilmWithJpaGraph(long id)
   {
      return filmJpaGraphRepository.findById(id);
   }
}
