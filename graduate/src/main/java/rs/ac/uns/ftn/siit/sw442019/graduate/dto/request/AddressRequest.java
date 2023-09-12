package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.*;
import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.WRONG_NAME;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {
    @NotNull
    @NotBlank(message = STREET_ERROR_MESSAGE)
    @Pattern(regexp = Constants.LEGIT_RE_CITY_AND_STREET_REG, message = WRONG_NAME)
    private String street;
    @NotNull
    @NotBlank(message = NUMBER_ERROR_MESSAGE)
    private String number;
    @NotNull
    @NotBlank(message = CITY_ERROR_MESSAGE)
    @Pattern(regexp = Constants.LEGIT_RE_CITY_AND_STREET_REG, message = WRONG_NAME)
    private String city;
    @NotNull
    @NotBlank(message = NUMBER_ERROR_MESSAGE)
    private double lon;
    @NotNull
    @NotBlank(message = NUMBER_ERROR_MESSAGE)
    private double lat;

}
