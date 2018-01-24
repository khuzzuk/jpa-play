package pl.silvermedia.jpaplay.film_2_namedQuery;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profit
{
   private String title;
   private Date paymentDate;
   private BigDecimal profit;
}
