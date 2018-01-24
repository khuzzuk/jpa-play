package pl.silvermedia.jpaplay.film_0a_jpaEntityGraph;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.silvermedia.jpaplay.film.Film;

@AllArgsConstructor
@RestController
public class FilmProgrammaticEntityGraphService
{
   private EntityManager entityManager;

   @GetMapping("withProgrammaticEntityGraph/{id}")
   public Film getWithEntityGraph(@PathVariable long id)
   {
      EntityGraph<?> graph = entityManager.createEntityGraph(Film.class);
      graph.addAttributeNodes("rentals");
      Subgraph<Object> subgraph = graph.addSubgraph("rentals");
      subgraph.addAttributeNodes("payments");

      Map<String, Object> hints = new HashMap<>();
      hints.put("javax.persistence.loadgraph", graph);

      return entityManager.find(Film.class, id, hints);
   }

}
