package pl.silvermedia.jpaplay.film;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import lombok.Data;
import pl.silvermedia.jpaplay.film.rental.Rental;

@Entity
@Data
public class Film
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

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "film_id")
   private Set<Inventory> inventories;
}
