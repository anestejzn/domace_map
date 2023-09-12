package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants;

import java.util.List;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferRequest {
    @NotNull
    @NotBlank(message = WRONG_NAME)
    @Pattern(regexp = Constants.LEGIT_NAME_REG, message = WRONG_NAME)
    private String name;
    private String description;
    @NotNull(message = POSITIVE_VALUE)
    private double price;
    @NotNull(message = WRONG_COL)
    private String colForPrice;
    @NotNull(message = REQUIRED_ID)
    private Long householdId;
    private List<String> photos;
    private String type;

}
