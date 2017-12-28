package pl.silvermedia.jpaplay.repos;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.silvermedia.jpaplay.db.Film;
import pl.silvermedia.jpaplay.db.Titled;

public interface FilmRepository extends JpaRepository<Film, Long>
{
   @Query("SELECT f from Film f")
   @EntityGraph(attributePaths = "inventories")
   Collection<Film> findAllWithInventories();

   Titled getTopById(long id);

   @Transactional
   Film findByTitle(String title);
}
