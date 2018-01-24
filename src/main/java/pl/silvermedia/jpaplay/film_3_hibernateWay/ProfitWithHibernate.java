package pl.silvermedia.jpaplay.film_3_hibernateWay;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

@Data
@Subselect("SELECT f.film_id as id, f.title AS title, sum(p.amount) AS profit "
                 + "FROM film f"
                 + "  JOIN inventory i ON f.film_id = i.film_id"
                 + "  JOIN rental r ON i.inventory_id = r.inventory_id"
                 + "  JOIN payment p ON r.rental_id = p.rental_id "
                 + "GROUP BY f.film_id")
@Entity(name = "ProfitWithHibernate")
@Synchronize({"film, inventory, rental, payment"})
public class ProfitWithHibernate
{
   @Id
   private int id;
   private String title;
   private double profit;
}

