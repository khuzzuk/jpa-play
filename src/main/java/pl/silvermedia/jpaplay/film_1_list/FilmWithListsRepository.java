package pl.silvermedia.jpaplay.film_1_list;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FilmWithListsRepository extends Repository<FilmWithLists, Long>
{
   @EntityGraph("list.film.rentals.payments")
   FilmWithLists findById(@Param("id") long id);
}
