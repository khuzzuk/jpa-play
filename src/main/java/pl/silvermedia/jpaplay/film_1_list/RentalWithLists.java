package pl.silvermedia.jpaplay.film_1_list;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import pl.silvermedia.jpaplay.film.Payment;

@Entity
@Table(name = "rental")
@Data
public class RentalWithLists
{
   @Id
   @Column(name = "rental_id")
   private long id;

   @Column(name = "rental_date")
   private Date rentalDate;

   @Column(name = "return_date")
   private Date returnDate;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "rental_id")
   private List<Payment> payments;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private Customer customer;
}
