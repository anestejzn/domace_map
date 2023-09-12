package rs.ac.uns.ftn.siit.sw442019.graduate.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.Role;
import rs.ac.uns.ftn.siit.sw442019.graduate.model.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private LocalDateTime lockedUntil;
    private Long verificationId;

    public UserResponse(Long id, String email, String password, String name, String surname, Role role, LocalDateTime lockedUntil) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.lockedUntil = lockedUntil;
    }

    public UserResponse(User user, Long verificationId){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole();
        this.lockedUntil = user.getLockedUntil();
        this.verificationId = verificationId;
    }

    public UserResponse(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole();
        this.lockedUntil = user.getLockedUntil();
    }
}
