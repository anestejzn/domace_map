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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdRequest {

    @NotNull
    @NotBlank(message = WRONG_NAME)
    @Pattern(regexp = Constants.LEGIT_NAME_REG, message = WRONG_NAME)
    private String name;
    @NotNull
    @NotBlank(message = WRONG_NAME)
    @Pattern(regexp = POSITIVE_WHOLE_NUMBER_REG, message = WRONG_NAME)
    private String registrationNumber;
    @NotNull
    @NotBlank(message = PHONENUMBER_ERROR_MESSAGE)
    @Pattern(regexp = PHONE_NUMBER_REG, message = WRONG_NAME)
    private String phoneNumber;
    private AddressRequest address;

    private Long userId;

}
