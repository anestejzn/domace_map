package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private UserResponse user;

    public LoginResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }
}
