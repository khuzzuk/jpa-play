package pl.silvermedia.jpaplay.film.rental;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<Rental, Long>
{
}
