package pl.silvermedia.jpaplay.film_2_namedQuery;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class ProgrammaticNamedQueryService
{
   private EntityManager entityManager;
   private static final String NAMED_QUERY = "profit-programmable";

   @PostConstruct
   private void addNamedQuery()
   {
      Query query = entityManager
            .createNativeQuery("SELECT f.title AS title, p.payment_date as paymentDate, sum(p.amount) AS profit "
                  + "FROM film f"
                  + "  JOIN inventory i ON f.film_id = i.film_id"
                  + "  JOIN rental r ON i.inventory_id = r.inventory_id"
                  + "  JOIN payment p ON r.rental_id = p.rental_id "
                  + "WHERE f.film_id = :id "
                  + "GROUP BY f.title, p.payment_date", "profitMapping");
      entityManager.getEntityManagerFactory().addNamedQuery(NAMED_QUERY, query);
   }

   @GetMapping("/searchProfitWithProgrammableAddedNamedQuery/{id}")
   public List<Profit> searchProfit(@PathVariable int id)
   {
      TypedQuery<Profit> namedQuery = entityManager.createNamedQuery(NAMED_QUERY, Profit.class);
      namedQuery.setParameter("id", id);
      return namedQuery.getResultList();
   }
}
