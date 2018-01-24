package pl.silvermedia.jpaplay.film.rental;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import pl.silvermedia.jpaplay.film.Payment;

@Entity
@Data
public class Rental
{
   @Id
   @Column(name = "rental_id")
   private long id;

   @Column(name = "rental_date")
   private Date rentalDate;

   @Column(name = "return_date")
   private Date returnDate;

   @OneToMany(fetch = FetchType.EAGER)
   @JoinColumn(name = "rental_id")
   private Set<Payment> payments;
}
