package pl.silvermedia.jpaplay.film_0_entityGraph;

import java.util.Collection;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface FilmEntityGraphRepository extends Repository<FilmByGraph, Long>
{
   @Query("SELECT f from FilmByGraph f "
                + "join f.rentals "
                + "join f.inventories "
                + "where f.id = :id")
   @EntityGraph(attributePaths = {"rentals", "inventories"})
   Collection<FilmByGraph> findByIdWithRentals(@Param("id") long id);
}
