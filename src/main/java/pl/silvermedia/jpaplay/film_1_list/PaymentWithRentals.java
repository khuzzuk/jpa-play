package pl.silvermedia.jpaplay.film_1_list;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "payment")
@Data
public class PaymentWithRentals
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "payment_id")
   private long id;
   private double amount;

   @Column(name = "rental_id")
   private long rentalId;

   @OneToMany(mappedBy = "payments")
   private List<RentalWithLists> rentals;
}
