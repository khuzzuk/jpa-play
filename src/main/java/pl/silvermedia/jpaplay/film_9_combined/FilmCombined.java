package pl.silvermedia.jpaplay.film_9_combined;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;
import pl.silvermedia.jpaplay.film_1_list.RentalWithLists;
import pl.silvermedia.jpaplay.film_2_namedQuery.Profit;

@NamedNativeQuery(name = "getProfits2", query =
      "SELECT f.title AS title, p.payment_date AS paymentDate, sum(p.amount) AS profit "
            + "FROM film f"
            + "  JOIN inventory i ON f.film_id = i.film_id"
            + "  JOIN rental r ON i.inventory_id = r.inventory_id"
            + "  JOIN payment p ON r.rental_id = p.rental_id "
            + "GROUP BY f.title, p.payment_date",
                  resultSetMapping = "profitMapping2")
@NamedNativeQuery(name = "getActorsPerMovies", query =
      "SELECT count(f.film_id) AS moviesCount, a.first_name AS firstName, a.last_name AS lastName "
            + "FROM actor a "
            + "JOIN film_actor fa ON fa.actor_id = a.actor_id "
            + "JOIN film f ON fa.film_id = f.film_id "
            + "GROUP BY a.first_name, a.last_name",
                  resultSetMapping = "actorMapping")
@SqlResultSetMapping(name = "profitMapping2",
                     classes = @ConstructorResult(targetClass = Profit.class,
                                                  columns = {
                                                        @ColumnResult(name = "title"),
                                                        @ColumnResult(name = "paymentDate"),
                                                        @ColumnResult(name = "profit")
                                                  }))
@SqlResultSetMapping(name = "actorMapping",
                     classes = @ConstructorResult(targetClass = ActorInMovies.class,
                                                  columns = {
                                                        @ColumnResult(name = "first_name"),
                                                        @ColumnResult(name = "last_name"),
                                                        @ColumnResult(name = "moviesCount")
                                                  }))
@NamedEntityGraph(name = "list.film.rentals.payments2",
                  attributeNodes = {
                        @NamedAttributeNode(value = "rentals", subgraph = "rentals2"),
                  },
                  subgraphs = @NamedSubgraph(name = "rentals2", attributeNodes = {
                        @NamedAttributeNode("payments"), @NamedAttributeNode("customer") }))
@NamedEntityGraph(name = "film.rentals.payments2",
                  attributeNodes = @NamedAttributeNode(value = "rentals", subgraph = "rentals3"),
                  subgraphs = @NamedSubgraph(name = "rentals3",
                                             attributeNodes = @NamedAttributeNode("payments")))
@Entity
@Table(name = "film")
@Data
public class FilmCombined
{
   @Id
   @Column(name = "film_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String title;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "inventory",
              joinColumns = @JoinColumn(name = "film_id"),
              inverseJoinColumns = @JoinColumn(name = "inventory_id"))
   private Set<RentalWithLists> rentals;
}
