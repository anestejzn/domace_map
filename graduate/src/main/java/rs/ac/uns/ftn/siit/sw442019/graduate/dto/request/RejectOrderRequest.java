package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RejectOrderRequest {
    private Long id;
    private String reason;
}
