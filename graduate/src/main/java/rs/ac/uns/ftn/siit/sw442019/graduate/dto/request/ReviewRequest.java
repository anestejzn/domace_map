package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

    private String text;
    @NotNull(message="Ocena proizvoda mora postojati.")
    @Positive(message="Ocena proizvoda mora biti broj izmedju 0 i 5.")
    private double rate;
    private Long offerId;
    private Long userId;
    private Long offerOrderId;

}
