package pl.silvermedia.jpaplay.film_1_list;

import java.util.List;

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

@Entity
@Table(name = "film")
@NamedEntityGraph(name = "list.film.rentals.payments",
                  attributeNodes = {
                        @NamedAttributeNode(value = "rentals", subgraph = "rentals"),
                        //@NamedAttributeNode("actors") //MultipleBagException
                  },
                  subgraphs = @NamedSubgraph(name = "rentals", attributeNodes = {
                        @NamedAttributeNode("payments"), @NamedAttributeNode("customer") }))
@Data
public class FilmWithLists
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
   private List<RentalWithLists> rentals;

   /* MultipleBagException
   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "actor_id")
   private List<Actor> actors;*/
}
