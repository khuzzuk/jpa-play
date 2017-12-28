package pl.silvermedia.jpaplay.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "inventory_id")
   private long id;
}
