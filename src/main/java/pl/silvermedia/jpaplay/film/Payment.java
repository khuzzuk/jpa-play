package pl.silvermedia.jpaplay.film;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Payment
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "payment_id")
   private long id;
   private double amount;

   @Column(name = "rental_id")
   private long rentalId;
}
