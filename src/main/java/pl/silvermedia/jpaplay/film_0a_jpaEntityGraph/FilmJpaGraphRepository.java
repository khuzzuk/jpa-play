package pl.silvermedia.jpaplay.film_0a_jpaEntityGraph;

import java.util.Collection;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FilmJpaGraphRepository extends Repository<FilmByJpaGraph, Long>
{
   @EntityGraph("film.rentals.payments")
   Collection<FilmByJpaGraph> findById(@Param("id") long id);
}
