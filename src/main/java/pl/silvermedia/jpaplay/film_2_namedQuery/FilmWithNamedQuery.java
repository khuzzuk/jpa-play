package pl.silvermedia.jpaplay.film_2_namedQuery;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;

@NamedNativeQuery(name = "getProfits", query =
      "SELECT f.title AS title, p.payment_date AS paymentDate, sum(p.amount) AS profit "
            + "FROM film f"
            + "  JOIN inventory i ON f.film_id = i.film_id"
            + "  JOIN rental r ON i.inventory_id = r.inventory_id"
            + "  JOIN payment p ON r.rental_id = p.rental_id "
            + "GROUP BY f.title, p.payment_date",
                  resultSetMapping = "profitMapping")
@SqlResultSetMapping(name = "profitMapping",
                     classes = @ConstructorResult(targetClass = Profit.class,
                                                  columns = {
                                                        @ColumnResult(name = "title"),
                                                        @ColumnResult(name = "paymentDate"),
                                                        @ColumnResult(name = "profit")
                                                  }))
@Entity
@Table(name = "film")
@Data
public class FilmWithNamedQuery
{
   @Id
   @Column(name = "film_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
}
