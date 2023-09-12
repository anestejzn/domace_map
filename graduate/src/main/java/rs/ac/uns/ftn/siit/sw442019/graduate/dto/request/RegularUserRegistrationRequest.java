package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.WRONG_PASSWORD;


@Getter
@Setter
public class RegularUserRegistrationRequest extends UserRequest {
    @NotBlank(message = WRONG_PASSWORD)
    @Pattern(regexp = Constants.LEGIT_PASSWORD_REG, message = WRONG_PASSWORD)
    private final String password;

    @NotBlank(message = WRONG_PASSWORD)
    @Pattern(regexp = Constants.LEGIT_PASSWORD_REG, message = WRONG_PASSWORD)
    private final String confirmPassword;

    public RegularUserRegistrationRequest(
            String email,
            String name,
            String surname,
            String password,
            String confirmPassword
    ) {
        super(email, name, surname);
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
