package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long userId;
    private AddressRequest address;
    private String phoneNumber;
    private List<OrderItem> items;

}
