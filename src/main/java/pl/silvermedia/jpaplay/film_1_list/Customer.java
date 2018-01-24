package pl.silvermedia.jpaplay.film_1_list;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Customer
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "customer_id")
   private int id;

   @Column(name = "first_name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   //Cannot fetch it with entity graph on Film level
   //@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
   //private List<RentalWithLists> rentals;
}
