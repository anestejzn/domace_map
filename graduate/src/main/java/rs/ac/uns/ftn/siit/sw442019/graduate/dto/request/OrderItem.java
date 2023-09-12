package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Long offerId;
    private Integer quantity;
    private double price;

}
