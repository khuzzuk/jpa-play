package pl.silvermedia.jpaplay.db;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

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
}
