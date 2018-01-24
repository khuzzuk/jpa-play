package pl.silvermedia.jpaplay.film_2_namedQuery;

import java.util.List;

import javax.persistence.EntityManager;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FilmWithNamedQueryService
{

   private final FilmWithNamedQueryRepository filmWithNamedQueryRepository;
   private final EntityManager entityManager;

   @SuppressWarnings("unchecked")
   @RequestMapping("filmsCustom/profits1")
   List<Profit> getProfits1()
   {
      return entityManager.createNativeQuery(
            "SELECT f.title AS title, p.payment_date as paymentDate, sum(p.amount) AS profit "
                  + "FROM film f"
                  + "  JOIN inventory i ON f.film_id = i.film_id"
                  + "  JOIN rental r ON i.inventory_id = r.inventory_id"
                  + "  JOIN payment p ON r.rental_id = p.rental_id "
                  + "GROUP BY f.title, p.payment_date", "profitMapping").getResultList();
   }

   @SuppressWarnings("unchecked")
   @RequestMapping("filmsCustom/profits2")
   List<Profit> getProfits2()
   {
      return filmWithNamedQueryRepository.getProfits();
   }

   @SuppressWarnings("unchecked")
   @RequestMapping("filmsCustom/profits3")
   List<Profit> getProfits3()
   {
      return entityManager.createNativeQuery(
      "SELECT f.title AS \"title\", p.payment_date as \"paymentDate\", sum(p.amount) AS \"profit\" "
            + "FROM film f"
            + "  JOIN inventory i ON f.film_id = i.film_id"
            + "  JOIN rental r ON i.inventory_id = r.inventory_id"
            + "  JOIN payment p ON r.rental_id = p.rental_id "
            + "GROUP BY f.title, p.payment_date")
            .unwrap(Query.class)
            .setResultTransformer(Transformers.aliasToBean(Profit.class)) //camelCase issues
            .getResultList();
   }
}
