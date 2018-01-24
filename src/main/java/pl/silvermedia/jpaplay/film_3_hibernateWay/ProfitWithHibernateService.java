package pl.silvermedia.jpaplay.film_3_hibernateWay;

import javax.persistence.EntityManager;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProfitWithHibernateService
{
   private final EntityManager entityManager;

   @GetMapping("profitsBy/{id}")
   public ProfitWithHibernate getProfitFor(@PathVariable int id)
   {
      return entityManager.createQuery(
            "select p from ProfitWithHibernate p where p.id = :id", ProfitWithHibernate.class)
            .setParameter("id", id)
            .getSingleResult();
   }
}

/*
Hibernate will execute wrapped statement:

SELECT
  profitwith0_.film_id AS film_id1_0_,
  profitwith0_.profit  AS profit2_0_,
  profitwith0_.title   AS title3_0_
FROM (SELECT
        f.filmId      AS filmId,
        f.title       AS title,
        sum(p.amount) AS profit
      FROM film f
        JOIN inventory i ON f.film_id = i.film_id
        JOIN rental r ON i.inventory_id = r.inventory_id
        JOIN payment p ON r.rental_id = p.rental_id
      GROUP BY f.film_id) profitwith0_
WHERE profitwith0_.film_id = ?;
 */