package pl.silvermedia.jpaplay.film;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.silvermedia.jpaplay.film_2_namedQuery.Profit;

public interface FilmRepository extends JpaRepository<Film, Long>
{
   @Query(name = "getProfits") //Profit is not managed entity
   List<Profit> getProfits();

   Titled getTopById(@Param("id") long id);

   @Transactional
   Film findByTitle(String title);
}
