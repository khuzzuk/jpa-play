package pl.silvermedia.jpaplay.filmEager;

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
import javax.persistence.Table;

import lombok.Data;
import pl.silvermedia.jpaplay.film.rental.Rental;

@Entity
@Table(name = "film")
@Data
public class FilmEager
{
   @Id
   @Column(name = "film_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String title;

   @OneToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "inventory",
              joinColumns = @JoinColumn(name = "film_id"),
              inverseJoinColumns = @JoinColumn(name = "inventory_id"))
   private Set<Rental> rental;
}
