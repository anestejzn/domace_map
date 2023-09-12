package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerifyMailResponse {
    private Long id;

    private int securityCode;

    private String hashId;

    public VerifyMailResponse(Long id, int securityCode, String hashId) {
        this.id = id;
        this.securityCode = securityCode;
        this.hashId = hashId;
    }
}
