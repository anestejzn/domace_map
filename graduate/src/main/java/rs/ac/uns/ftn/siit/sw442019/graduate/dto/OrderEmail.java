package rs.ac.uns.ftn.siit.sw442019.graduate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEmail {
    private String userName;
    private String address;
    private String city;
    private String phoneNumber;
    private String numberOfOrder;
    private List<ItemEmail> items;
    private double totalPrice;
}
