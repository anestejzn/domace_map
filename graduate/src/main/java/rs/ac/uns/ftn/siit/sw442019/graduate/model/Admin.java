package rs.ac.uns.ftn.siit.sw442019.graduate.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="admin")
@Setter
@Getter
@NoArgsConstructor
public class Admin extends User {

    public Admin(String email, String password, String name, String surname, String salt,  int failedAttempts, LocalDateTime lockedUntil, Role role) {
        super(email, password, name, surname, salt, failedAttempts, lockedUntil, role);
    }
}