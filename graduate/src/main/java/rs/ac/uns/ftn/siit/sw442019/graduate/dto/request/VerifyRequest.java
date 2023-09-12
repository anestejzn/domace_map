package rs.ac.uns.ftn.siit.sw442019.graduate.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static rs.ac.uns.ftn.siit.sw442019.graduate.util.Constants.WRONG_SECURITY_CODE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {
    private Long verifyId;
    private int securityCode;
}
