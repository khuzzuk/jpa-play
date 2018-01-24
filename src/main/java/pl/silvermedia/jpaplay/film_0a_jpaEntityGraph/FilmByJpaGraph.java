package pl.silvermedia.jpaplay.film_0a_jpaEntityGraph;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import pl.silvermedia.jpaplay.film.rental.Rental;

@Entity
@Table(name = "film")
@NamedEntityGraph(name = "film.rentals.payments",
                  attributeNodes = @NamedAttributeNode(value = "rentals", subgraph = "rentals"),
                  subgraphs = @NamedSubgraph(name = "rentals",
                                             attributeNodes = @NamedAttributeNode("payments")))
@Data
public class FilmByJpaGraph
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
   private Set<Rental> rentals;
}
