package pl.silvermedia.jpaplay.film_2_namedQuery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FilmWithNamedQueryRepository extends JpaRepository<FilmWithNamedQuery, Long>
{
   @Query(name = "getProfits") //Profit is not managed entity
   List<Profit> getProfits();
}
